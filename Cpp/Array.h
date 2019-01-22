
#ifndef ALGS4_ARRAY_H
#define ALGS4_ARRAY_H

#include <algorithm>
#include <initializer_list>
#include <iterator>
#include <stdexcept>

//#include "Iterator.h"

namespace CSn
{

/**
	size_type is an unsigned integer type used for the array
	size.  It can also be used for array indices.
*/
using index = unsigned int;

/**
	invalid is a special index value representing an
	invalid index.  This is generally used as a "not found"
	return value.
*/
const index invalid = static_cast<index>(-1);

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
		iterator is the iterator type used for an Array.
	*/
	using iterator = T * ;

private:

	index cap;
	T * data;

#if 0
	InnerIterator<T> * create_iterator(bool at_end) const noexcept
	{
		return new Array<T>::ForwardIterator(*this, at_end ? cap : 0);
	}
#endif

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
	explicit Array(index size)
		: data(size == 0 ? nullptr : new T[size]), cap(size) {}

	/**
		Creates an array with the given size and sets every entry
		to the given value.  Note that all elements are default
		constructed before being set to the given value.

		@param[in] size The initial size of the Array.
		@param[in] value The initial value for all the Array elements.
	*/
	Array(index size, const T & value) : data(new T[size]), cap(size)
	{
		for (index i = 0; i < cap; i++)
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
		: cap(static_cast<index>(std::distance(init.begin(), init.end()))),
		data(new T[cap])
	{
		std::copy(init.begin(), init.end(), this->begin());
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
	Array(const Array & other)
		: cap(other.cap), data(new T[cap])
	{
		std::copy(other.begin(), other.end(), this->begin());
	}

	/**
		Assignment operator.  This is marked as deleted until needed.
	*/
	Array & operator=(const Array & rhs)
	{
		Array<T> a(rhs);
		std::swap(*this, a);
		return *this;
	}

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
	index size() const noexcept
	{
		return cap;
	}

	/**
		Index operator.  Throws std::out_of_range if the
		index is greater or equal to the capacity.

		@param[in] idx The index of the item to retrieve.
	*/
	T & operator[](index idx)
	{
		if (idx >= cap) throw std::out_of_range("Array::operator[]");
		return data[idx];
	}

	/**
		const index operator.  Throws std::out_of_range if the
		index is greater or equal to the capacity.

		@param[in] idx The index of the item to retrieve.
		*/
	const T & operator[](index idx) const
	{
		if (idx >= cap) throw std::out_of_range("Array::operator[] const");
		return data[idx];
	}

	/**
		Returns an iterator which references the first element of the Array.

		@return An iterator which references the first element of the Array.
	*/
	iterator begin() const noexcept
	{
		return &data[0];
	}

	/**
		Returns an iterator which references the last element of the Array.

		@return An iterator which references the last element of the Array.
	*/
	iterator end() const noexcept
	{
		return &data[cap];
	}

#if 0
	// We maye eventually need fancier iterator types for Stack
	// and Queue, but Array itself only needs the T* iterators
	// returned above for begin() and end().

	/**
		Implements InnerIterator to iterate forward through the array
		starting at the given index.
	*/
	class ForwardIterator : public InnerIterator<T>
	{
	private:
		T * pos;
		T * end;
		ForwardIterator(T * p, T * e) : pos(p), end(p) {}
	public:
		ForwardIterator(const Array & a, size_t idx)
			: pos(&a.data[idx]), end(&a.data[a.cap]) {}
		~ForwardIterator() {}
		T & dereference() const override
		{
			if (pos == end)
				throw std::out_of_range("ForwardIterator::dereference");
			return *pos;
		}
		void increment() noexcept override
		{
			pos += 1;
		}
		bool equal_to(const InnerIterator<T> * rhs) const noexcept override
		{
			return pos == dynamic_cast<const ForwardIterator *>(rhs)->pos;
		}
		InnerIterator<T> * clone() const override
		{
			return new ForwardIterator(pos, end);
		}
	};

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
		T & dereference() const override
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

#endif
};

/**
	operator== - this exists mainly for testing.  It assumes that
	the type parameter satisfies the EqualityComparable concept.
*/
template<typename T>
bool operator==(const Array<T> & lhs, const Array<T> & rhs)
{
	if (lhs.size() != rhs.size()) return false;
	for (index i = 0; i < lhs.size(); i++)
		if (lhs[i] != rhs[i]) return false;
	return true;
}

}

#endif
