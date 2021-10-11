"""
Title: my_string_solution
Reference: https://docs.python.org/3/library/stdtypes.html#str

This is a basic custom implementation of a Python string. It is based off the
language standard

Execution: python my_string_solution.py
"""

class MyString:

    # Store the string data as a character list
    data = []

    # Constructor that takes
    def __init__(self, string:str):
        data = list(string)

    # Compare 
