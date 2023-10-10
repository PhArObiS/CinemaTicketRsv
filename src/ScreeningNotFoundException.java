public class ScreeningNotFoundException extends RuntimeException {
    public ScreeningNotFoundException(String message) {
        super(message);
    }
}

//    A few notes:
//
//        Uniqueness: Creating domain-specific exception types, like this one, can help in diagnosing problems quickly when they arise, as it provides more context than generic exceptions. By throwing or catching ScreeningNotFoundException, you immediately know the type of problem you're dealing with.
//
//        Constructors: Currently, you're only providing a constructor that accepts a message. Depending on your use case, you might also want to provide other constructors, for instance:
//
//        A default constructor.
//        A constructor that accepts a Throwable cause (useful for exception chaining).
//        A constructor that accepts both a message and a cause.
//        This allows more flexibility in how you create and handle the exception.
//
//        Documentation: Consider adding JavaDoc comments to your exception class. While it seems simple, documenting your exception (and other classes) provides context for other developers who might use your code. This is particularly useful for explaining when and why this exception might be thrown.
//
//        Package and Visibility: Ensure that this class is placed in an appropriate package, and its visibility aligns with its intended use. If you're only using it within a specific package, package-private might be sufficient. If it's going to be used across packages, public is appropriate.
//
//        Overall, it's a clean and straightforward implementation of a custom exception. Depending on how you're planning to use it, this might be all you need!