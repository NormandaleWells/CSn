
#ifndef ALGS4_ARRAY_H
#define ALGS4_ARRAY_H

#include <algorithm>
#include <initializer_list>
#include <iterator>
#include <stdexcept>

#include "Iterator.h"

namespace CSn
{

/**
	Array is a simple array class that does bounds checking.

	The type parameter T is assumed to satisfy the
	DefaultConstructible and CopyConstructible concepts.
*/

template<typename T>
class Array
{
public:

	/**
		value_type is the type parameter; that is, the type of
		objects stored in the stack.
	*/
	using value_type = T;

	/**
		size_type is an unsigned integer type used for the array
		size.  It can also be used for array indices.
	*/
	using size_type = size_t;

private:

	size_type cap;
	T * data;

public:

	/**
		Creates an empty array.
	*/
	Array() : data(nullptr), cap(0) {}

	/**
		Creates an array with the given size.  The elements are
		default constructed.

		@param[in] size The initial size of the Array.
	*/
	explicit Array(size_type size)
		: data(size == 0 ? nullptr : new T[size]), cap(size) {}

	/**
		Creates an array with the given size and sets every entry
		to the given value.  Note that all elements are default
		constructed before being set to the given value.

		@param[in] size The initial size of the Array.
		@param[in] value The initial value for all the Array elements.
	*/
	Array(size_type size, const T & value) : data (new T[size]), cap(size)
	{
		for (size_type i = 0; i <  cap; i++)
			data[i] = value;
	}

	/**
		Creates an Array object and initializes the elements from
		the given initializer list. Note that all elements are
		default constructed before being set to the value from
		the initializer list.

		@param[in] init An initializer_list<T> providing the
		initial default values.  The initial size of the Array
		is the number of items in the initializer list.
	*/
	Array(std::initializer_list<T> init)
		: cap(std::distance(init.begin(), init.end())), data(new T[cap])
	{
		int i = 0;
		for (auto iter = init.begin(); iter != init.end(); iter++)
			data[i++] = *iter;
	}

	/**
		Destructor.
	*/
	~Array()
	{
		delete[] data;
	}

	/**
		Copy constructor.  This is marked as deleted until needed.
	*/
	Array(const Array & other) = delete;

	/**
		Assignment operator.  This is marked as deleted until needed.
	*/
	Array & operator=(const Array & rhs) = delete;

	/**
		Move constructor.
	*/
	Array(Array && other)
		: cap(other.cap), data(other.data)
	{
		other.cap = 0;
		other.data = nullptr;
	}

	/**
		Move assignment operator.
	*/
	Array & operator=(Array && rhs)
	{
		delete[] data;

		cap = rhs.cap;
		data = rhs.data;

		rhs.cap = 0;
		rhs.data = nullptr;

		return *this;
	}

	/**
		Returns the size of the Array.
	*/
	size_type size() const noexcept
	{
		return cap;
	}

	/**
		Index operator.  Throws std::out_of_range if the
		index is greater or equal to the capacity.

		@param[in] idx The index of the item to retrieve.
	*/
	T & operator[](size_type idx)
	{
		if (idx >= cap) throw std::out_of_range("Array::operator[]");
		return data[idx];
	}

	/**
		const index operator.  Throws std::out_of_range if the
		index is greater or equal to the capacity.

		@param[in] idx The index of the item to retrieve.
		*/
	const T & operator[](size_type idx) const
	{
		if (idx >= cap) throw std::out_of_range("Array::operator[] const");
		return data[idx];
	}

	/**
		Implements InnerIterator to iterate backward through the array
		starting from a given index.
	*/
	class BackwardIterator : public InnerIterator<T>
	{
	private:
		T * pos;
		T * end;
		BackwardIterator(T * p, T * e) : pos(p), end(e) {}
	public:
		BackwardIterator(const Array & a, int idx) : pos(&a.data[idx]), end(a.data) {}
		~BackwardIterator() {}
		const T & dereference() const override
		{
			if (pos == end)
				throw std::out_of_range("BackwardIterator::dereference*");
			return *(pos - 1);
		}
		void increment() noexcept override
		{
			pos -= 1;
		}
		bool equal_to(const InnerIterator<T> * rhs) const noexcept override
		{
			return pos == dynamic_cast<const BackwardIterator *>(rhs)->pos;
		}
		InnerIterator<T> * clone() const override
		{
			return new BackwardIterator(pos, end);
		}
	};
};

/**
	operator== - this exists mainly for testing.  It assumes that
	the type parameter satisfies the EqualityComparable concept.
*/
template<typename T>
bool operator==(const Array<T> & lhs, const Array<T> & rhs)
{
	if (lhs.size() != rhs.size()) return false;
	for (typename Array<T>::size_type i = 0; i < lhs.size(); i++)
		if (lhs[i] != rhs[i]) return false;
	return true;
}

}

#endif
