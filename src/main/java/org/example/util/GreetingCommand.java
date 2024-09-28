package org.example.util;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GreetingCommand {
    private String name;
    private List<String> arguments = new ArrayList<>();
}
