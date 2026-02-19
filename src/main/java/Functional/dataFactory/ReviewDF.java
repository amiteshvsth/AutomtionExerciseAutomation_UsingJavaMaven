package Functional.dataFactory;

import Functional.dataObject.ReviewDO;

public class ReviewDF {

    public static ReviewDO fillReviewDetails(){
        ReviewDO user = new ReviewDO();
        user.setName("Amitesh");
        user.setEmail("amiteshvashishth@yopmail.com");
        user.setReviewText("This product is very nice");
        return user;
    }


}

