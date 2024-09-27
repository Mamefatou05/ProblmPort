package com.ProjetJavaApp.web.dto.projections;

import com.ProjetJavaApp.data.entities.Apprenant;
import com.ProjetJavaApp.data.enums.PresenceEnum;

import java.util.Date;

public interface EmergementProjection {
    Long getId();
    Date getDateEntree();
    Date getDateSortie();
    Apprenant getApprenant();
    PresenceEnum getPresence();
}
