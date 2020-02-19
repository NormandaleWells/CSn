package edu.normandale.csn;

public class LLMap<Key, Value> implements Map<Key, Value> {

	LinkedList<KV> ll = new LinkedList<>();

	private class KV {
		final Key key;
		Value value;
		KV(Key k, Value v) {
			key = k;
			value = v;
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj.getClass().equals(key.getClass()))
				return obj.equals(key);
			if (obj instanceof LLMap.KV)
				return ((KV) obj).key.equals(key);
			return false;
		}
	}

	@Override
	public void put(Key k, Value v) {
		KV kv = ll.find(k);
		if (kv != null)
			kv.value = v;
		else {
			kv = new KV(k,v);
			ll.addFront(kv);
		}
	}

	@Override
	public Value get(Key k) {
		KV kv = ll.find(k);
		return kv == null ? null : kv.value;
	}

	@Override
	public boolean contains(Key k) {
		KV kv = ll.find(k);
		return kv != null;
	}

	@Override
	public void delete(Key k) {
		ll.remove(k);
	}

	@Override
	public int size() {
		return ll.size();
	}

	@Override
	public boolean isEmpty() {
		return ll.isEmpty();
	}

	// We can't use LinkedList.iterator() directly, because
	// our linked list is a list of KV objects, not a list
	// of keys.  So we'll create a queue of Key objects,
	// and return that.
	// This means our keys() function is O(N), but we can
	// assume that the caller is going to run through all
	// the keys to do something, so we really aren't
	// affecting the overall asymptotic behavior.
	@Override
	public Iterable<Key> keys() {
		LLQueue<Key> q = new LLQueue<>();
		for (KV kv : ll) {
			q.enqueue(kv.key);
		}
		return q;
	}
}
