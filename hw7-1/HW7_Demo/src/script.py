# generate input
import random
import string

def random_stock_symbol():
    return ''.join(random.choices(string.ascii_uppercase, k=3))

def random_price():
    return round(random.uniform(5.0, 50.0), 2)

def random_quantity():
    return random.randint(100000, 10000000)

def generate_add():
    symbol = random_stock_symbol()
    price = random_price()
    min_quantity = random_quantity()
    max_quantity = min_quantity * random.randint(2, 10)
    return f"ADD {symbol} {price} {min_quantity} {max_quantity}"

def generate_remove(stock_list):
    if not stock_list:
        return generate_add()
    symbol = random.choice(stock_list)
    return f"REMOVE {symbol}"

def generate_search(stock_list):
    if not stock_list:
        return generate_add()
    symbol = random.choice(stock_list)
    return f"SEARCH {symbol}"

def generate_update(stock_list):
    if len(stock_list) < 2:
        return generate_add()
    symbol_from = random.choice(stock_list)
    symbol_to = random_stock_symbol()
    price = random_price()
    min_quantity = random_quantity()
    max_quantity = min_quantity * random.randint(2, 10)
    return f"UPDATE {symbol_from} {symbol_to} {price} {min_quantity} {max_quantity}"

def main():
    node_commands = 100
    add_commands = 100
    remove_commands = 30
    search_commands = 1000
    update_commands = 0

    commands = []

    stock_list = []

    #generate add, remove, update and search command given number of
    for _ in range(add_commands): 
        add_cmd = generate_add()
        commands.append(add_cmd)
        stock_list.append(add_cmd.split()[1])

    for _ in range(remove_commands):
        commands.append(generate_remove(stock_list))

    for _ in range(search_commands):
        commands.append(generate_search(stock_list))

    for _ in range(update_commands):
        commands.append(generate_update(stock_list))

    # Mix the commands
    random.shuffle(commands)

    
    # Write to a file inputfile.txt
    with open('input.txt', 'w') as f:
        for _ in range (1000): # Initialy 1000 add method
            add_cmd = generate_add()
            f.write(add_cmd + '\n')

        for command in commands: # after add random 
            f.write(command + '\n')

if __name__ == "__main__":
    main()
