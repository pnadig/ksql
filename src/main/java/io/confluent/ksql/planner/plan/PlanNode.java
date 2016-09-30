package io.confluent.ksql.planner.plan;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.confluent.ksql.planner.Schema;

import java.util.List;

import static java.util.Objects.requireNonNull;

public abstract class PlanNode {

    private final PlanNodeId id;

    protected PlanNode(PlanNodeId id)
    {
        requireNonNull(id, "id is null");
        this.id = id;
    }

    @JsonProperty("id")
    public PlanNodeId getId()
    {
        return id;
    }

    public abstract Schema getSchema();

    public abstract List<PlanNode> getSources();

    public <C, R> R accept(PlanVisitor<C, R> visitor, C context)
    {
        return visitor.visitPlan(this, context);
    }
}