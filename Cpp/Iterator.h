
#ifndef ALGS4_ITERATOR_H
#define ALGS4_ITERATOR_H

#include <memory>

namespace CSn
{

template<typename T>
class InnerIterator
{
public:

	virtual const T & dereference() const = 0;
	virtual void increment() noexcept = 0;
	virtual bool equal_to(const InnerIterator<T> * other) const noexcept = 0;
	virtual InnerIterator<T> * clone() const = 0;
};

template<typename T>
class Iterator : public std::iterator<std::input_iterator_tag, T>
{
private:

	std::unique_ptr<InnerIterator<T>> inner;

public:

	Iterator(InnerIterator<T> * ii) : inner(ii) {}

	Iterator(const Iterator & other) : inner(other.inner->clone()) {}

	Iterator(Iterator && other) : inner(std::move(other.inner)) {}

	Iterator & operator=(const Iterator & other)
	{
		if (this != &other)
		{
			inner = other.inner->clone();
		}
		return *this;
	}

	Iterator & operator=(Iterator && other) noexcept
	{
		if (this != &other)
		{
			inner = std::move(other.inner);
		}
		return *this;
	}

	const T & operator*() const
	{
		return inner->dereference();
	}

	const T * operator->() const
	{
		return &inner->dereference();
	}

	Iterator operator++(int) noexcept
	{
		Iterator i(*this);
		++*this;
		return i;
	}

	Iterator & operator++() noexcept
	{
		inner->increment();
		return *this;
	}

	bool operator==(const Iterator & rhs) const noexcept
	{
		return inner->equal_to(rhs.inner.get());
	}

	bool operator!=(const Iterator & rhs) const noexcept
	{
		return !(*this == rhs);
	}
};

}

#endif
