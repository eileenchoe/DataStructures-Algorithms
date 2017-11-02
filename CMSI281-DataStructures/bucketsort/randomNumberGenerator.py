import sys
import random

def main():
    f = open("randomDoublesToSort.txt", "w+")
    numRand = int(sys.argv[1])
    min = int(sys.argv[2])
    max = int(sys.argv[3])

    for i in range(numRand):
        rand = random.uniform(min, max)
        f.write(str(rand) + "\n")

    f.close()

main()
