// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    long meetingRequestDuration = request.getDuration();
    Collection<Event> eventsWithRequestedAttendees = getEventsWithRequestedAttendees(events, request.getAttendees());

    Collection<TimeRange> availabeRanges = findAllAvailableTimeRanges(eventsWithRequestedAttendees);
    Collection<TimeRange> result = findViableTimeRangesForRequest(availabeRanges, meetingRequestDuration);

    return result;
  }

  Collection<Event> getEventsWithRequestedAttendees(Collection<Event> events, Collection<String> meetingRequestAttendees){
    Collection<Event> result = new ArrayList<Event>();
    for(Event event: events){
      for(String attendee : meetingRequestAttendees){
        if (event.getAttendees().contains(attendee)){
        result.add(event);
        }
      }    
    } 
    return result;
  }

  Collection<TimeRange> findAllAvailableTimeRanges(Collection<Event> eventsWithRequestedAttendees){
    Collection<TimeRange> availabeRanges = new ArrayList<TimeRange>();
    int min = -1;
    for(int minute=TimeRange.WHOLE_DAY.start(); minute<TimeRange.WHOLE_DAY.end()+1; minute++){
      boolean isMinuteInEvent = false;
      for(Event event: eventsWithRequestedAttendees){
        isMinuteInEvent = event.getWhen().contains(minute); //if events range does or does not include current minute
        if (isMinuteInEvent){
          break;
        }
      }
      if (isMinuteInEvent){//if current minute is within range of current event in loop
        if (min!=-1){// if min has been set
          TimeRange foundMeetingRange = TimeRange.fromStartDuration(min,minute-min);
          availabeRanges.add(foundMeetingRange);
          min = -1;
        }
      }
      else{//Establish a start of the time range
        if (min==-1){
          min = minute;
        }
        else{
          if (minute==TimeRange.WHOLE_DAY.end()){
            TimeRange foundMeetingRange = TimeRange.fromStartDuration(min,minute-min);
            availabeRanges.add(foundMeetingRange);
          }
        }
      }
    }
    return availabeRanges;
  }

  Collection<TimeRange> findViableTimeRangesForRequest(Collection<TimeRange> availabeRanges , long meetingRequestDuration){
    Collection<TimeRange> result = new ArrayList<TimeRange>();    
    for(TimeRange available: availabeRanges){
      if (available.duration() >= meetingRequestDuration){
        result.add(available);    
      }
    }
    return result;
  }
}