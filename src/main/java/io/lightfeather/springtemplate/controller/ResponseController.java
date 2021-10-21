package io.lightfeather.springtemplate.controller;

import io.lightfeather.springtemplate.dto.Supervisors;
import io.lightfeather.springtemplate.dto.Form;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.*;
import org.springframework.util.StringUtils;

import org.apache.commons.lang3.math.NumberUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ResponseController {

    //External API to pull managers from
    private final String GET_API = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";

    //Comparator to sort first by jurisdiction, then last name, then first name.
    Comparator<Supervisors> compare = Comparator
        .comparing(Supervisors::getJurisdiction)
        .thenComparing(Supervisors::getLastName)
        .thenComparing(Supervisors::getFirstName);

    //WebClientBuilder
    @Autowired
    private WebClient.Builder webClientBuilder;

    //Home page with hints
    @RequestMapping("/")
    public String home() {
        return "Please visit localhost:8080/supervisors to get a filtered and ordered list of supervisors. Please visit localhost:8080/submit for a mock POST.";
    }

    //GET endpoint to pull list from API, filter, sort, and display supervisor data.
    @GetMapping("/supervisors")
    public String getSupervisors() {

        //Webclient
        Flux<Supervisors> supervisor = webClientBuilder.build()
                .get()
                .uri(GET_API)
                .retrieve()
                .bodyToFlux(Supervisors.class);

        //Create list of Supervisors objects
        List<Supervisors> output = supervisor.collectList().block();

        //Sort the list
        Collections.sort(output, compare);

        //Remove entries with numeric jurisdictions
        output.removeIf((Supervisors s) -> NumberUtils.isCreatable(s.getJurisdiction()));

        //Create new list of strings
        List<String> string = new ArrayList<String>();

        //Loop to format entries as required into list of strings
        for(int i = 0; i < output.size(); i++){
            string.add(output.get(i).getJurisdiction()+" - "+output.get(i).getLastName()+", "+output.get(i).getFirstName());
        }

        //Display data
        return string.toString();
    }

    //POST endpoint to submit data. Methods are explicitly stated to avoid 405 error when opening in the browser.
    @RequestMapping(value = "/submit", method = {RequestMethod.GET, RequestMethod.POST})
    public void postSubmit() {

        //Form object to hold our dummy data.
        Form form = new Form();
        form.setFirstName("Tony");
        form.setLastName("Stark");
        form.setEmail("Tony.Stark@starkindustries.com");
        form.setPhoneNumber("1231231234");
        form.setSupervisor("The Council");

        //Webclient - If I had more time, I would have added parameter requirements.
        WebClient webclient = webClientBuilder.build();
        Mono<Form> formout = webclient.post()
            .uri("http://localhost:8080/submit")
            .bodyValue(form)
            .retrieve()
            .bodyToMono(Form.class);

        //Log data to console.
        System.out.println(form.toString());    
    }
    
}
