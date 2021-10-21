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

    private final String GET_API = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";

    Comparator<Supervisors> compare = Comparator
        .comparing(Supervisors::getJurisdiction)
        .thenComparing(Supervisors::getLastName)
        .thenComparing(Supervisors::getFirstName);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/")
    public String home() {
        return "Please visit http://localhost:8080/supervisors to get a list of supervisors.";
    }

    @GetMapping("/supervisors")
    public String getSupervisors() {

        Flux<Supervisors> supervisor = webClientBuilder.build()
                .get()
                .uri(GET_API)
                .retrieve()
                .bodyToFlux(Supervisors.class);

        List<Supervisors> output = supervisor.collectList().block();

        Collections.sort(output, compare);

        output.removeIf((Supervisors s) -> NumberUtils.isCreatable(s.getJurisdiction()));

        List<String> string = new ArrayList<String>();

        for(int i = 0; i < output.size(); i++){
            string.add(output.get(i).getJurisdiction()+" - "+output.get(i).getLastName()+", "+output.get(i).getFirstName());
        }
        
        return string.toString();
    }

    @RequestMapping(value = "/submit", method = {RequestMethod.GET, RequestMethod.POST})
    public String postSubmit() {

        Form form = new Form();
        form.setFirstName("Tony");
        form.setLastName("Stark");
        form.setEmail("Tony.Stark@starkindustries.com");
        form.setPhoneNumber("1231231234");
        form.setSupervisor("The Council");

        WebClient webclient = webClientBuilder.build();

        Mono<Form> formout = webclient.post()
            .uri("http://localhost:8080/submit")
            .bodyValue(form)
            .retrieve()
            .bodyToMono(Form.class);

        return form.toString();
    }
    
}
