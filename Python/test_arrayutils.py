import unittest
import arrayutils


class TestFindFunctions(unittest.TestCase):

    def test_find(self):
        # test full array
        a = [27, 82, 41, 124, 62, 31, 94, 47]
        self.assertEqual(arrayutils.find(a, 27), 0)
        self.assertEqual(arrayutils.find(a, 82), 1)
        self.assertEqual(arrayutils.find(a, 47), 7)
        self.assertEqual(arrayutils.find(a, 0), -1)

        # test subrange
        self.assertEqual(arrayutils.find(a, 27, lo=2, hi=6), -1)
        self.assertEqual(arrayutils.find(a, 82, lo=2, hi=6), -1)
        self.assertEqual(arrayutils.find(a, 41, lo=2, hi=6), 2)
        self.assertEqual(arrayutils.find(a, 31, lo=2, hi=6), 5)
        self.assertEqual(arrayutils.find(a, 94, lo=2, hi=6), -1)
        self.assertEqual(arrayutils.find(a, 0, lo=2, hi=6), -1)

        # test full array with multiple equal values
        a = [2, 2, 3, 4, 3, 5]
        self.assertEqual(arrayutils.find(a, 2), 0)
        self.assertEqual(arrayutils.find(a, 3), 2)
        self.assertEqual(arrayutils.find(a, 4), 3)

        # test subrange with multiple equal values
        self.assertEqual(arrayutils.find(a, 2, lo=1, hi=6), 1)
        self.assertEqual(arrayutils.find(a, 3, lo=1, hi=6), 2)
        self.assertEqual(arrayutils.find(a, 3, lo=3, hi=6), 4)

    def test_find_if(self):
        a = [27, 82, 41, 124, 62, 31, 94, 47]

        def greater_than_100(n):
            return n > 100

        self.assertEqual(arrayutils.find_if(a, greater_than_100), 3)
        self.assertEqual(arrayutils.find_if(a, greater_than_100, lo=2, hi=6), 3)
        self.assertEqual(arrayutils.find_if(a, greater_than_100, lo=0, hi=3), -1)
        self.assertEqual(arrayutils.find_if(a, greater_than_100, lo=4, hi=-1), -1)

    def test_count(self):
        # test full array
        a = [1, 2, 3, 2, 1, 2, 3]
        self.assertEqual(arrayutils.count(a, 1), 2)
        self.assertEqual(arrayutils.count(a, 2), 3)
        self.assertEqual(arrayutils.count(a, 3), 2)

        # test subrange
        self.assertEqual(arrayutils.count(a, 1, lo=1, hi=4), 0)
        self.assertEqual(arrayutils.count(a, 2, lo=1, hi=6), 3)

    def test_count_if(self):
        a = [1, 2, 3, 2, 1, 2, 3]

        def is_even(n):
            return n % 2 == 0;

        self.assertEqual(arrayutils.count_if(a, is_even), 3)
        self.assertEqual(arrayutils.count_if(a, is_even, lo=1, hi=6), 3)
        self.assertEqual(arrayutils.count_if(a, is_even, lo=2, hi=5), 1)


if __name__ == '__main__':
    unittest.main()
