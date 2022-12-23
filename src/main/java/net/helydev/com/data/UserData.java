package net.helydev.com.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Joako (22/12/2022 | 13:55)
 **/

@Getter
@Setter
@AllArgsConstructor
public class UserData {

    private UUID uuid;
    private Map<Integer, String> items;
}
