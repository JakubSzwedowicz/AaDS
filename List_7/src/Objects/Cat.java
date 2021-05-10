package Objects;

import Visitor.Visitor;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class Cat implements LivingBeing {
    private final String name;

    Cat(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCat(this);
    }
}
