def algorithm(n):
    if n <= 0:
        return 1
    return n * algorithm(n - 1)