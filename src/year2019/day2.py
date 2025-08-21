def calc_res(instructions: list[int], noun: int, verb: int) -> int:
    instructions[1] = noun
    instructions[2] = verb

    ip = 0
    while ip < len(instructions):
        match instructions[ip]:
            case 1:
                numl = instructions[instructions[ip + 1]]
                numr = instructions[instructions[ip + 2]]
                instructions[instructions[ip + 3]] = numl + numr
                ip += 4
            case 2:
                numl = instructions[instructions[ip + 1]]
                numr = instructions[instructions[ip + 2]]
                instructions[instructions[ip + 3]] = numl * numr
                ip += 4
            case 99:
                break
            case _:
                print("ERRO ERRO")
                exit(1)
    return instructions[0]


nums = []
with open("inputs/day2.txt", "r") as f:
    nums = [int(val) for val in f.read().split(",")]


# parte 1
print(calc_res(nums[:], 12, 2))

# parte 2
a = 0
b = 0

while a < 99 or (a == 99 and b <= 99):
    res = calc_res(nums[:], a, b)
    if res == 19690720:
        print(f"a={a} b={b}")
        break

    b += 1
    if b > 99:
        b = 0
        a += 1
