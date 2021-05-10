package Objects;

import Visitor.Visitor;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 10.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class Student implements LivingBeing {
    private final int number;

    Student(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitStudent(this);
    }
}
