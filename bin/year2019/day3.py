from dataclasses import dataclass
from copy import copy
import matplotlib.pyplot as plt
import math

VERT = 1
HORZ = 2


@dataclass()
class Ponto:
    x: int
    y: int


@dataclass()
class Linha:
    tipo: int  # 1 vertical, 2 horizontal
    pi: Ponto
    pf: Ponto


def arruma_linha(ln: Linha) -> None:
    if ln.tipo == VERT:
        if ln.pi.y > ln.pf.y:
            temp = copy(ln.pi)
            ln.pi = ln.pf
            ln.pf = temp
    else:
        if ln.pi.x > ln.pf.x:
            temp = copy(ln.pi)
            ln.pi = ln.pf
            ln.pf = temp


def intersect(l1: Linha, l2: Linha) -> Ponto | None:
    if l1.tipo == l2.tipo:
        return None

    if l1.tipo == VERT:
        x = l1.pi.x
        y = l2.pi.y
        if l1.pi.y <= y and l1.pf.y >= y and l2.pi.x <= x and l2.pf.x >= x:
            return Ponto(x, y)
    else:
        x = l2.pi.x
        y = l1.pi.y
        if l2.pi.y <= y and l2.pf.y >= y and l1.pi.x <= x and l1.pf.x >= x:
            return Ponto(x, y)

    return None


linhas: list[Linha] = []
intersecs: list[Ponto] = []

buf1 = []
buf2 = []

with open("inputs/day3.txt", "r") as f:
    buf = f.read()
    buf1 = buf.split("\n")[0].split(",")
    buf2 = buf.split("\n")[1].split(",")


TESTE1 = """R75,D30,R83,U83,L12,D49,R71,U7,L72
U62,R66,U55,R34,D71,R55,D58,R83"""
b1 = TESTE1.split("\n")[0].split(",")
b2 = TESTE1.split("\n")[1].split(",")

print(b1, b2)

x, y = 0, 0
dir = 0
for val in buf1:
    p1 = Ponto(x, y)
    match val[0]:
        case "R":
            x += int(val[1:])
            dir = HORZ
        case "L":
            x -= int(val[1:])
            dir = HORZ
        case "D":
            y -= int(val[1:])
            dir = VERT
        case "U":
            y += int(val[1:])
            dir = VERT

    p2 = Ponto(x, y)
    lin = Linha(dir, p1, p2)
    arruma_linha(lin)
    linhas.append(lin)


x, y = 0, 0
dir = 0
linhas2 = []
for val in buf2:
    p1 = Ponto(x, y)
    match val[0]:
        case "R":
            x += int(val[1:])
            dir = HORZ
        case "L":
            x -= int(val[1:])
            dir = HORZ
        case "D":
            y -= int(val[1:])
            dir = VERT
        case "U":
            y += int(val[1:])
            dir = VERT

    p2 = Ponto(x, y)
    lin = Linha(dir, p1, p2)
    arruma_linha(lin)
    linhas2.append(lin)
    for lin_cmp in linhas:
        inter = intersect(lin, lin_cmp)
        if inter is not None:
            intersecs.append(inter)

min_dist = float("inf")
for inter in intersecs:
    if inter.x == 0 and inter.y == 0:
        continue
    min_dist = min(min_dist, abs(inter.x) + abs(inter.y))

print("parte 1:", min_dist)


def line_contains(line: Linha, p: Ponto):
    return (
        line.pi.x <= p.x and line.pf.x >= p.x and line.pi.y <= p.y and line.pf.y >= p.y
    )


min_sum = float("inf")
for inter in intersecs:
    sum1, sum2 = 0, 0
    for line in linhas:
        if line_contains(line, inter):
            sum1 += abs(line.pi.x - inter.x) + abs(line.pi.y - inter.y)
            break
        sum1 += abs(line.pi.x - line.pf.x) + abs(line.pi.y - line.pf.y)

    for line in linhas2:
        if line_contains(line, inter):
            sum2 += abs(line.pi.x - inter.x) + abs(line.pi.y - inter.y)
            break
        sum2 += abs(line.pi.x - line.pf.x) + abs(line.pi.y - line.pf.y)

    sum_tot = sum1 + sum2
    min_sum = min(min_sum, sum_tot)

print(min_sum)


# for lin in linhas:
#     plt.plot([lin.pi.x, lin.pf.x], [lin.pi.y, lin.pf.y], "k-")
#
# for lin in linhas2:
#     plt.plot([lin.pi.x, lin.pf.x], [lin.pi.y, lin.pf.y], "g-")
#
# plt.show()

