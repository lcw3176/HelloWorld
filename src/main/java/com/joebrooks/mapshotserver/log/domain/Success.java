package com.joebrooks.mapshotserver.log.domain;

import com.joebrooks.mapshotserver.user.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Success {
    private Date date;
    private User user;
}
