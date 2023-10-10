import java.util.Objects;

/**
 * Represents a movie with details like title, duration, classification, synopsis, director, and release year.
 */
public class Movie {
    private final String title;
    private final int duration;
    private final String classification;
    private final String synopsis;
    private final String director;
    private final int releaseYear;

    Movie(MovieBuilder builder) {
        this.title = builder.title;
        this.duration = builder.duration;
        this.classification = builder.classification;
        this.synopsis = builder.synopsis;
        this.director = builder.director;
        this.releaseYear = builder.releaseYear;
    }

    public Movie(String title, String director, int releaseYear) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = 0;  // default value
        this.classification = "";  // default value
        this.synopsis = "";  // default value
    }

    public Movie(int id, String title, String director, int releaseYear) {
        // Ignoring the 'id' parameter because there's no field for it in the class.
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = 0;  // default value
        this.classification = "";  // default value
        this.synopsis = "";  // default value
    }

    public Movie(int id, String title, String director, int releaseYear, int duration, String classification, String synopsis) {
        // Ignoring the 'id' parameter because there's no field for it in the class.
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.classification = classification;
        this.synopsis = synopsis;
    }

    public static class MovieBuilder {
        private String title;
        private int duration;
        private String classification;
        private String synopsis;
        private String director;
        private int releaseYear;

        public MovieBuilder setTitle(String title) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty or null");
            }
            this.title = title;
            return this;
        }

        public MovieBuilder setDuration(int duration) {
            if (duration <= 0) {
                throw new IllegalArgumentException("Duration must be a positive value");
            }
            this.duration = duration;
            return this;
        }

        public MovieBuilder setClassification(String classification) {
            if (classification == null || classification.trim().isEmpty()) {
                throw new IllegalArgumentException("Classification cannot be empty or null");
            }
            this.classification = classification;
            return this;
        }

        public MovieBuilder setSynopsis(String synopsis) {
            if (synopsis == null || synopsis.trim().isEmpty()) {
                throw new IllegalArgumentException("Synopsis cannot be empty or null");
            }
            this.synopsis = synopsis;
            return this;
        }

        public MovieBuilder setDirector(String director) {
            if (director == null || director.trim().isEmpty()) {
                throw new IllegalArgumentException("Director cannot be empty or null");
            }
            this.director = director;
            return this;
        }

        public MovieBuilder setReleaseYear(int releaseYear) {
            if (releaseYear <= 0) {
                throw new IllegalArgumentException("Release year must be a positive value");
            }
            this.releaseYear = releaseYear;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    public String getTitle() {
        return this.title;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getClassification() {
        return this.classification;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public String getDirector() {
        return this.director;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", classification='" + classification + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return duration == movie.duration &&
                releaseYear == movie.releaseYear &&
                title.equals(movie.title) &&
                classification.equals(movie.classification) &&
                synopsis.equals(movie.synopsis) &&
                director.equals(movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration, classification, synopsis, director, releaseYear);
    }
}
