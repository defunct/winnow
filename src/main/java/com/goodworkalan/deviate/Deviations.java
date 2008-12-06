package com.goodworkalan.deviate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Deviations<T>
{
    private final Node root;
    
    private final Map<List<Set<Match>>, List<T>> mapOfValues;

    private final int size;
    
    public Deviations(int size)
    {
        if (size < 1)
        {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.root = new Node(null);
        this.mapOfValues = new HashMap<List<Set<Match>>, List<T>>();
    }
    
    public void put(List<Set<Match>> listOfMatches, T value)
    {
        if (listOfMatches.size() != size)
        {
            throw new IllegalArgumentException();
        }
        List<Set<Match>> key = new ArrayList<Set<Match>>(size);
        Node node = root;
        for (int i = 0; i < size; i++)
        {
            node = getChild(node, listOfMatches.get(i));
            key.add(node.matches);
        }
        List<T> values = mapOfValues.get(key);
        if (values == null)
        {
            values = new ArrayList<T>();
            mapOfValues.put(key, values);
        }
        values.add(value);
    }
    
    public List<T> get(Object...objects)
    {
        List<T> values = new ArrayList<T>();
        List<Set<Match>> path = new ArrayList<Set<Match>>();
        for (Node node : root.children)
        {
            descend(values, path, node, objects, 0);
        }
        return values;
    }
    
    private void descend(List<T> values, List<Set<Match>> path, Node node, Object[] objects, int index)
    {
        if (node.matches(objects[index]))
        {
            List<Set<Match>> pathOfChild = new ArrayList<Set<Match>>(path);
            pathOfChild.add(node.matches);
            if (index + 1 == size)
            {
                values.addAll(mapOfValues.get(pathOfChild));
            }
            else
            {
                for (Node child : node.children)
                {
                    descend(values, pathOfChild, child, objects, index + 1);
                }
            }
        }
    }

    private Node getChild(Node parent, Set<Match> matches)
    {
        Node child = null;
        for (Node node : parent.children)
        {
            if (node.matches.equals(matches))
            {
                child = node;
                break;
            }
        }
        if (child == null)
        {
            child = new Node(matches);
            parent.children.add(child);
        }
        return child;
    }
    
    final static class Node
    {
        public final Set<Match> matches;
        
        public final List<Node> children;
        
        public Node(Set<Match> matches)
        {
            this.matches = matches;
            this.children = new ArrayList<Node>();
        }
        
        public boolean matches(Object object)
        {
            for (Match match : matches)
            {
                if (match.match(object))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
