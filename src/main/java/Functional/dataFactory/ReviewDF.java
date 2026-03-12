package Functional.dataFactory;

import Functional.dataObject.ReviewDO;

public class ReviewDF extends BaseDF{

    public static ReviewDO fillReviewDetails(){
        ReviewDO user = new ReviewDO();
        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setReviewText(faker.lorem().sentence(8));
        return user;
    }


}

