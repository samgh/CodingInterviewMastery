"""
Title: SetAndMapSolutions

This file contains the solutions for the Set and Map exercises in
the DS & Algos Primer. If you have not already attempted these exercises,
we highly recommend you complete them before reviewing the solutions here.

Execution: python set_and_map_solutions.py
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
        self._data = []
        for i in range(capacity):
            self._data.append([])

        self._size = 0

    """
    Add a value to the set

    Time Complexity: O(size(set) + len(val))
    Space Complexity: O(1)
    """
    def add(self, val: str):
        # Find the bucket index
        idx = hash(val) % len(self._data)

        # If the value isn't in the bucket, add it to the bucket
        if not val in self._data[idx]:
            self._data[idx].append(val)
            self._size = self._size+1

    """
    Does the set contain val?

    Time Complexity: O(size(set) + len(val))
    Space Complexity: O(1)
    """
    def contains(self, val: str) -> bool:
        # Find the bucket and see if it contains the desired value
        idx = hash(val) % len(self._data)
        return val in self._data[idx]

    """
    Remove value from the set

    Time Complexity: O(size(set) + len(val))
    Space Complexity: O(1)
    """
    def remove(self, val: str):
        # Find the bucket index
        idx = hash(val) % len(self._data)

        if val in self._data[idx]:
            self._data[idx].remove(val)
            self._size = self._size-1

    """
    Convert the set into a list

    Time Complexity: O(n * average value length)
    Space Complexity: O(1)
    """
    def toList(self):
        return [item for sublist in self._data for item in sublist]

    """
    Exercise 2.1: Resize the set backing array

    We probably want to do this once size > 2*capacity to keep our lookup
    times low

    Time Complexity: O(capacity + size(set) * average value length)
    Space Complexity: O(1)
    """
    def resize(self, new_capacity: int):
        # Create new backing list
        new_data = []
        for i in range(new_capacity):
            new_data.append([])

        # For every value in the current set, we need to rehash it to see
        # what bucket it goes in
        for l in self._data:
            for s in l:
                idx = hash(s) % len(new_data)
                new_data[idx].append(s)

        self._data = new_data

"""
Exercise 1.2: Implement a HashMap
"""
class MyHashMap:

    """
    Constructor
    """
    def __init__(self, capacity: int):
        self._data = []
        for i in range(capacity):
            self._data.append([])

        self._size = 0

    """
    Add a key-value pair to the map. If the key already exists, update
    the corresponding value

    Time Complexity: O(size(map) + len(key))
    Space Complexity: O(1)
    """
    def put(self, key: str, val: str):
        # Find the bucket index
        idx = hash(key) % len(self._data)

        # Check whether the map already contains the key and if it does,
        # remove that entry
        for e in self._data[idx]:
            if e[0] == key:
                new_entry = (e[0], val)
                self._data[idx].remove(e)

        # The key is not in the map, so add the key/value pair
        self._data[idx].append((key, val))
        self._size = self._size+1

    """
    Get the value in the map for the corresponding key

    Time Complexity: O(size(map) + len(key))
    Space Complexity: O(1)
    """
    def get(self, key: str) -> str:
        # Find the bucket index
        idx = hash(key) % len(self._data)

        # Find the key/value pair with the matching key
        for e in self._data[idx]:
            if e[0] == key:
                return e[1]

        # If they key is not in the map, return None
        return None

    """
    Return true if the key is in the map

    Time Complexity: O(size(map) + len(key))
    Space Complexity: O(1)
    """
    def contains_key(self, key: str) -> bool:
        # Find the bucket index
        idx = hash(key) % len(self._data)

        # See if the key exists in the current bucket
        for e in self._data[idx]:
            if e[0] == key:
                return True

        return False

    """
    Remove the key-value pair from the map

    Time Complexity: O(size(map) + len(key))
    Space Complexity: O(1)
    """
    def remove(self, key: str):
        # Find the bucket index
        idx = hash(key) % len(self._data)

        for i in range(len(self._data[idx])):
            if self._data[idx][i][0] == key:
                self._data[idx].pop(i)
                self._size = self._size-1
                return

    """
    Exercise 2.2: Resize the backing array to the new capacity

    Time Complexity: O(capacity + size(map) * average value length)
    Space Complexity: O(1)
    """
    def resize(self, new_capacity: int):
        # Create new backing list
        new_data = []
        for i in range(new_capacity):
            new_data.append([])

        # For every value in the current set, we need to rehash it to see
        # what bucket it goes in
        for l in self._data:
            for s in l:
                idx = hash(s[0]) % len(new_data)
                new_data[idx].append(s)

        self._data = new_data

# Test Cases
if __name__ == '__main__':
    s = MyHashSet(10)
    s.add("abc")
    s.add("bcd")
    s.add("cde")
    print(s.contains("bcd"))
    s.remove("bcd")
    print(s.contains("bcd"))
    print(s.toList())
    s.resize(20)
    print(s.toList())

    m = MyHashMap(10)
    m.put("abc","bcd")
    m.put("123","234")
    m.put("xyz","abc")
    m.put("abc","xyz")
    print(m.contains_key("abc"))
    print(m.get("abc"))
    m.remove("abc")
    print(m.contains_key("abc"))
    print(m.get("abc"))
    m.resize(100)
    print(m.contains_key("xyz"))
    print(m.get("xyz"))
