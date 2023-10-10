import java.time.LocalDateTime;
import java.util.List;

public interface Searchable {
    List<Movie> searchByTitle(String title) throws MovieNotFoundException;
    List<Screening> searchByTime(LocalDateTime time) throws ScreeningNotFoundException;

    List<Screening> searchByTime(LocalDateTime fromTime, LocalDateTime toTime) throws ScreeningNotFoundException;
}
//
//    The Searchable interface provides a contract for classes that implement search functionality for movies and screenings. This kind of separation is good for design as it promotes the Single Responsibility Principle (SRP) and allows for multiple implementations of the search functionality without affecting the client code.
//
//        Here are some observations and potential improvements:
//
//        Documentation: Consider adding JavaDoc comments to the interface and its methods. This provides guidance to developers who implement or use this interface. For example, explain the parameters, return values, and exceptions thrown.
//
//        Exception Handling:
//
//        When the searchByTitle method does not find a movie with the given title, it throws a MovieNotFoundException. Similarly, the methods searchByTime throw a ScreeningNotFoundException when no screenings are found. Consider whether throwing an exception is the right approach for a "not found" situation. Another approach might be to return an empty list, which signifies no results. This avoids the overhead of exception handling for a relatively common scenario.
//        If you choose to stick with exceptions, ensure the exceptions carry meaningful messages. This can assist in debugging and also in relaying meaningful feedback to the user.
//        Return Type:
//
//        The methods return a List. This is flexible, but you might want to consider if a Set (to ensure uniqueness) or another collection type is more appropriate based on the business requirements.
//        Ensure that the methods never return null. Instead, return an empty list if no matches are found. This avoids potential NullPointerException issues in the calling code.
//        Method Naming: The naming of methods is descriptive and indicates their purpose well. This is good for clarity and readability.
//
//        Parameter Naming: The parameters in the searchByTime methods are named time, fromTime, and toTime. The naming is clear and denotes the purpose of each parameter.
//
//        Flexibility: Depending on future requirements, you might need to extend the interface to support more search criteria. Designing the interface in a way that it's easy to extend (without breaking existing implementations) can be beneficial.
//
//        Remember, the key with interfaces is to ensure they remain relatively stable over time, as changes can break implementing classes. If you foresee frequent changes, you might want to look into patterns that allow more flexibility, like the Adapter or Strategy patterns.