package Objects;

import Visitor.Visitor;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public interface LivingBeing {
    void accept(Visitor visitor);
}
