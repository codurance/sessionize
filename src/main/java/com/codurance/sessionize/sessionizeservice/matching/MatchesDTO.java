package com.codurance.sessionize.sessionizeservice.matching;

import com.codurance.sessionize.sessionizeservice.pairing.PairingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchesDTO {

  List<PairingDTO> matches;

}
