package edu.wctc.bean.demo5;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author jlombardo
 */
public class LogPhaseListener implements PhaseListener {

public long startTime;

    public void afterPhase(PhaseEvent event) {
            if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
                    long endTime = System.nanoTime();
                    long diffMs = (long) ((endTime - startTime) * 0.000001);

                    System.out.println("Execution Time = " + diffMs + "ms");

            }

            System.out.println("Executed Phase " + event.getPhaseId());

    }

    public void beforePhase(PhaseEvent event) {

            if (event.getPhaseId() == PhaseId.RESTORE_VIEW) {
                    startTime = System.nanoTime();
            }
    }

    public PhaseId getPhaseId() {
            return PhaseId.ANY_PHASE;
    }
}
