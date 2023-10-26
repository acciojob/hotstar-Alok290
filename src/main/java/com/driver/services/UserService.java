package com.driver.services;


import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebSeriesRepository webSeriesRepository;


    public Integer addUser(User user){

        //Jut simply add the user to the Db and return the userId returned by the repository
        userRepository.save(user);
       int ans= user.getId();
        return ans;
    }

    public Integer getAvailableCountOfWebSeriesViewable(Integer userId){

        //Return the count of all webSeries that a user can watch based on his ageLimit and subscriptionType
        //Hint: Take out all the Webseries from the WebRepository

        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.get();

        Subscription subscription = user.getSubscription();
        SubscriptionType subscriptionType = subscription.getSubscriptionType();

        List<WebSeries> webSeriesList = webSeriesRepository.findAll();

        int count =0;
        if(subscriptionType.equals(SubscriptionType.BASIC)){
            for(WebSeries w : webSeriesList){
                if(subscriptionType.equals(SubscriptionType.BASIC))
                    count++;
            }
        } else if (subscriptionType.equals(SubscriptionType.PRO)) {
            for(WebSeries w :webSeriesList) {
                if (subscriptionType.equals(SubscriptionType.BASIC) || subscriptionType.equals(SubscriptionType.PRO)) {
                    count++;
                }
            }

        }
        else{
            for(WebSeries w :webSeriesList){
                count++;
            }
        }


        return count;
    }


}
