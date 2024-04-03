package br.com.ileonnard.passin.services;

import br.com.ileonnard.passin.domain.attendee.Attendee;
import br.com.ileonnard.passin.domain.checkin.CheckIn;
import br.com.ileonnard.passin.dto.attendee.AttendeeDetails;
import br.com.ileonnard.passin.dto.attendee.AttendeesListResponseDTO;
import br.com.ileonnard.passin.repositories.AttendeeRepository;
import br.com.ileonnard.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream()
                .map(attendee -> {
                    Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
                    LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
                    return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
                }).toList();
        return new AttendeesListResponseDTO(attendeeDetailsList);
    }
}
