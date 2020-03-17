public class Student implements StudentFinances, StudentGrades {
    private final Finances finances = new Finances();
    private final Grades grades = new Grades();

    @Override
    public Finances getFinances(){
        return this.finances;
    }
    public Grades getGrades(){
        return this.grades;
    }
}
