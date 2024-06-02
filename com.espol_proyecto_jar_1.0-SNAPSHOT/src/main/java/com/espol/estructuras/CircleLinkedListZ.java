/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.estructuras;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author joshz
 */
public class CircleLinkedListZ<E> implements List<E> {
    
    Node primero;
    Node ultimo;
    
    protected class Node{
        E contenido;
        Node sig;
        Node ant;
        Node(E e){
            this.contenido=e;
            this.sig=null;
            this.ant=null;
        }
    }
    
    @Override
    public int size() {
        int cont=1;
        Node prim = primero;
        if (primero == null) return 0;
        while (prim.sig != null){
            prim= prim.sig;
            cont++;
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return (primero==null);
    }
    
    @Override
    public boolean contains(Object o) {
        Node prim = primero;
        while (prim != null) {
        if (prim.contenido.equals(o)) {
            return true;
        }
        prim = prim.sig;
    }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("Collection cannot be null");
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null || c.isEmpty()) return false;
        for (E element : c) add(size(), element);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException("Index out of bounds");
        int currentIndex = index;
        for (E element : c) {
            add(currentIndex++, element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        primero=null;
        ultimo=null;
    }

    @Override
    public E get(int index) {
        if (primero == null) {
        throw new IndexOutOfBoundsException("List is empty");
        }
        Node actual = primero;
        int count = 0;
        do {
            if (count == index) {
                return actual.contenido;
            }
            actual = actual.sig;
            count++;
        } while (actual != primero);
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
    }

    @Override
    public E set(int index, E element) {
        if (primero == null) {
        throw new IndexOutOfBoundsException("List is empty");
        }

        Node actual = primero;
        int count = 0;
        do {
            if (count == index) {
                E viejo = actual.contenido;
                actual.contenido = element;
                return viejo;
            }
            actual = actual.sig;
            count++;
        } while (actual != primero);

        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
    }
    
    private Node getNode(int index) {
        Node actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.sig;
        }
        return actual;
    }
    
    @Override
    public void add(int index, E element) {
        Node nuevo = new Node(element);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            nuevo.sig = nuevo;
            nuevo.ant = nuevo;
        } else if (index == 0) {
            nuevo.sig = primero;
            nuevo.ant = ultimo;
            primero.ant = nuevo;
            ultimo.sig = nuevo;
            primero = nuevo;
        } else if (index == size()) {
            nuevo.sig = primero;
            nuevo.ant = ultimo;
            ultimo.sig = nuevo;
            primero.ant = nuevo;
            ultimo = nuevo;
        } else {
            Node actual = getNode(index);
            Node anterior = actual.ant;
            nuevo.sig = actual;
            nuevo.ant = anterior;
            anterior.sig = nuevo;
            actual.ant = nuevo;
        }
    }

    @Override
    public E remove(int index) {
        Node aEliminar = getNode(index);
        E contenido = aEliminar.contenido;
        if (size() == 1) {
            primero = null;
            ultimo = null;
        } else if(aEliminar == primero) {
            ultimo.sig = primero.sig;
            primero.sig.ant = ultimo;
            primero = primero.sig;
        } else if(aEliminar == ultimo) {
            primero.ant = ultimo.ant;
            ultimo.ant.sig = primero;
            ultimo = ultimo.ant;
        } else{
            aEliminar.ant.sig = aEliminar.sig;
            aEliminar.sig.ant = aEliminar.ant;
        }
        return contenido;
    }

    @Override
    public int indexOf(Object o) {
        Node actual = primero;
        for (int i = 0; i < size(); i++) {
            if (actual.contenido.equals(o)) {
                return i;
            }
            actual = actual.sig;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
