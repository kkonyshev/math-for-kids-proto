package mfk;

public interface ContextIterator<Context> {
	boolean hasNext();
	Context next();
	void process();
}