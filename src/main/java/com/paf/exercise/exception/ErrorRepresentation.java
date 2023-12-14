package com.paf.exercise.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRepresentation {

  private int code;
  private String reason;
  private String message;
  private String referenceError;

}
