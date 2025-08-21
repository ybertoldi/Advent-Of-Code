import pprint
from copy import copy as objcopy


cache = {}


def find_soil(cur, maps, i, lim) -> int:
    if (cur, i) in cache:
        return cache[(cur, i)]

    if i == lim:
        return cur

    for line in maps[i]:
        if cur >= line[1] and cur < line[1] + line[2]:
            cur = line[0] + (cur - line[1])
            result = find_soil(cur, maps, i + 1, lim)
            cache[(cur, i)] = result
            return result

    cache[(cur, i)] = cur
    return find_soil(cur, maps, i + 1, lim)


def solve(seeds, maps):
    min_soil = float("inf")
    for seed in seeds:
        min_soil = min(min_soil, find_soil(seed, maps, 0, len(maps)))

    return min_soil


maps = []

buf = [ln.strip() for ln in open("inputs/day5_ex.txt", "r").readlines()]
seeds = [int(s) for s in buf[0].split()[1:]]

i = 1
curmap = []
while i < len(buf):
    if not buf[i]:
        if curmap:
            maps.append(curmap)

        curmap = []
        i += 1
    else:
        curmap.append([int(v) for v in buf[i].split()])

    i += 1

if curmap:
    maps.append(curmap)

print("part1:", solve(seeds, maps))


range_maps = []
for map in maps:
    range_maps.append(
        [
            (
                range(line[0], line[0] + line[2] - 1),
                range(line[1], line[1] + line[2] - 1),
            )
            for line in map
        ]
    )

seed_ranges: list[range] = [
    range(seeds[i], seeds[i] + seeds[i + 1]) for i in range(0, len(seeds), 2)
]


def subrange(target: range, r: range) -> range | None:
    if r.stop < target.start or r.start > target.stop:
        return None

    start = max(target.start, r.start)
    end = min(target.stop, r.stop)
    return range(start, end)


cur_seeds = seed_ranges[:]
next_seeds = []
pprint.pprint(cur_seeds)

for maprange in range_maps:
    for linerange in maprange:
        for seedrange in cur_seeds:
            sub = subrange(linerange[1], seedrange)
            if sub:
                newstart = sub.start - linerange[1].start + linerange[0].start
                newend = sub.stop - linerange[1].start + linerange[0].start
                next_seeds.append(range(newstart, newend))
            else:
                next_seeds.append(objcopy(seedrange))
        pprint.pprint(cur_seeds)
        cur_seeds = next_seeds[:]
        next_seeds = []

pprint.pprint(cur_seeds)
pprint.pprint(next_seeds)
