import org.chocosolver.solver.search.strategy.assignments.DecisionOperator;
import org.chocosolver.solver.search.strategy.decision.Decision;
import org.chocosolver.solver.search.strategy.decision.IntDecision;
import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.PoolManager;

public class CustomStrategy extends AbstractStrategy<IntVar> {

    IntVar x, y;
    PoolManager<IntDecision> pool = new PoolManager();

    CustomStrategy(IntVar x, IntVar y){
        this.x = x;
        this.y = y;
    }

    public Decision<IntVar> getDecision() {
        IntDecision d = pool.getE();
        if(d==null) d = new IntDecision(pool);
        IntVar next = null;
        for(IntVar v:vars){
            if(!v.isInstantiated()){
                next = v; break;
            }
        }
        if(next == null){
            return null;
        }else {
            // next decision is assigning nextVar to its lower bound
            d.set(next,next.getLB(), DecisionOperator.int_eq);
            return d;
        }
    }
}
