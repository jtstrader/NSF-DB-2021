package com.nsfdb.api.analytics.aggregations;

import com.nsfdb.api.models.Monkey;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class FamilyTreeNode extends DefaultMutableTreeNode {
    Monkey monkey;
    FamilyTreeNode[] sibling;
    FamilyTreeNode[] child;

    public FamilyTreeNode(Monkey monkey) {
        super(monkey.getAnimal_id());
        this.monkey = monkey;
        sibling = new FamilyTreeNode[1];
        child = new FamilyTreeNode[1];
    }

    @Override
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
    }

    public Monkey getMonkey() { return monkey; }

    public FamilyTreeNode[] getSibling() { return sibling; }

    public FamilyTreeNode[] getChild() { return child; }
}