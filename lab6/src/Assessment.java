public class Assessment implements Keyable<String> {
    String key, grade;

    Assessment(String key, String grade) {
        this.key = key;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String getKey() {
        return key;
    }
    public String toString() {
        return "{" + key + ": " + grade + "}";
    }
}
