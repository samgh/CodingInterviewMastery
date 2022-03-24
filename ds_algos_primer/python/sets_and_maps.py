"""
Title: SetsAndMaps

This file contains the template for the Set and Map exercises in the
DS & Algos Primer. Fill in the exercises here and refer to
set_and_map_solutions.py for the complete code samples.

Execution: python sets_and_maps.py
"""

from typing import List

"""
Exercise 1.1: Implement a HashSet
"""
class MyHashSet:

    """
    Constructor
    """
    def __init__(self, capacity: int):
        # INSERT YOUR SOLUTION HERE

    """
    Add a value to the set

    Time Complexity:
    Space Complexity:
    """
    def add(self, val: str):
        # INSERT YOUR SOLUTION HERE

    """
    Does the set contain val?

    Time Complexity:
    Space Complexity:
    """
    def contains(self, val: str) -> bool:
        # INSERT YOUR SOLUTION HERE

    """
    Remove value from the set

    Time Complexity:
    Space Complexity:
    """
    def remove(self, val: str):
        # INSERT YOUR SOLUTION HERE

    """
    Convert the set into a list

    Time Complexity:
    Space Complexity:
    """
    def toList(self):
        # INSERT YOUR SOLUTION HERE

    """
    Exercise 2.1: Resize the set backing array

    We probably want to do this once size > 2*capacity to keep our lookup
    times low

    Time Complexity:
    Space Complexity:
    """
    def resize(self, new_capacity: int):
        # INSERT YOUR SOLUTION HERE

"""
Exercise 1.2: Implement a HashMap
"""
class MyHashMap:

    """
    Constructor
    """
    def __init__(self, capacity: int):
        # INSERT YOUR SOLUTION HERE

    """
    Add a key-value pair to the map. If the key already exists, update
    the corresponding value

    Time Complexity:
    Space Complexity:
    """
    def put(self, key: str, val: str):
        # INSERT YOUR SOLUTION HERE

    """
    Get the value in the map for the corresponding key

    Time Complexity:
    Space Complexity:
    """
    def get(self, key: str) -> str:
        # INSERT YOUR SOLUTION HERE

    """
    Return true if the key is in the map

    Time Complexity:
    Space Complexity:
    """
    def contains_key(self, key: str) -> bool:
        # INSERT YOUR SOLUTION HERE

    """
    Remove the key-value pair from the map

    Time Complexity:
    Space Complexity:
    """
    def remove(self, key: str):
        # INSERT YOUR SOLUTION HERE

    """
    Exercise 2.2: Resize the backing array to the new capacity

    Time Complexity:
    Space Complexity:
    """
    def resize(self, new_capacity: int):
        # INSERT YOUR SOLUTION HERE

"""
Exercise 1.3: Flatten a dictionary

Time Complexity:
Space Complexity:
"""
def flatten_dictionary(my_dict: dict) -> dict:
    # INSERT YOUR SOLUTION HERE


# Test Cases
if __name__ == '__main__':
    # INSERT YOUR TEST CASES HERE
