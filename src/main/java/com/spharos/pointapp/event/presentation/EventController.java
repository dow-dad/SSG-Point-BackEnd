package com.spharos.pointapp.event.presentation;

import com.spharos.pointapp.event.application.EventService;
import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.vo.EventCreate;
import com.spharos.pointapp.event.vo.EventGetOut;
import com.spharos.pointapp.event.vo.EventUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;

    @PostMapping("/event")
    public void createEvent(@RequestBody EventCreate eventCreate) {
        log.info("{}", eventCreate);
        ModelMapper mapper = new ModelMapper();
        EventCreateDto eventCreateDto = mapper.map(eventCreate, EventCreateDto.class);
        eventService.createEvent(eventCreateDto);

    }

    @PutMapping("/event")
    public void updateEvent(@RequestBody EventUpdate eventUpdate) {
        log.info("{}", eventUpdate);
        ModelMapper mapper = new ModelMapper();
        EventUpdateDto eventUpdateDto = mapper.map(eventUpdate, EventUpdateDto.class);
        eventService.updateEvent(eventUpdateDto, eventUpdate.getEventId());
    }


    @GetMapping("/event")
    public EventGetOut getEventByEventId(@RequestParam(name = "eventId", defaultValue = "")  Long eventId) {
        log.info("{}", eventId);
        ModelMapper mapper = new ModelMapper();
        EventGetDto eventGetDto = eventService.getEvent(eventId);
        return mapper.map(eventGetDto, EventGetOut.class);
    }

    @GetMapping("/event/all")
    private List<EventGetOut> getAllEvents() {
        ModelMapper mapper = new ModelMapper();
        List<EventGetDto> eventGetDtoList = eventService.getEvents();
        log.info("{}", eventGetDtoList);
        List<EventGetOut> eventGetOutList = new ArrayList<>();
        eventGetDtoList.forEach(
                eventGetDto -> eventGetOutList.add(
                    mapper.map(eventGetDto, EventGetOut.class)
                )
        );
        return eventGetOutList;
    }

    @DeleteMapping("/event")
    private void deleteEvent(@RequestParam(name = "eventId", defaultValue = "")  Long eventId) {
        eventService.deleteEvent(eventId);
    }
}
