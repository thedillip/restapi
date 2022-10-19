package com.rest.api.response;

import java.util.List;

import com.rest.api.dto.StateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateDetailsResponse {
	private String code;
	private String name;
	private String capital;
	private List<StateDTO> states;
}
