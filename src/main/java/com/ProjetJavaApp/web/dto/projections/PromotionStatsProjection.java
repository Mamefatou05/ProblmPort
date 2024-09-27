package com.ProjetJavaApp.web.dto.projections;

import java.util.List;

public interface PromotionStatsProjection {
    Long getPromotionId();
    String getLibelle();
    int getNombreApprenants();
    int getNombreApprenantsActifs();
    int getNombreApprenantsInactifs();
    List<ReferentielStatsProjection> getReferentielsActifs();

    interface ReferentielStatsProjection {
        Long getId();
        String getLibelle();
        int getNombreApprenants();
    }
}
