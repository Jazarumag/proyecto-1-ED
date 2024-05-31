/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;

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
        Node newNode = new Node(e);
        if (primero == null) {
            primero = newNode;
            ultimo = newNode;
        } else {
            ultimo.sig = newNode;
            newNode.ant = ultimo;
            newNode.sig = primero;
            primero.ant = newNode;
            ultimo = newNode;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (primero == null) {
        return false;
        }
        Node actual = primero;
        do {
            if (actual.contenido.equals(o)) {
                if (actual == primero && actual == ultimo) {
                    primero = null;
                    ultimo = null;
                } else if (actual == primero) {
                    primero = primero.sig;
                    primero.ant = ultimo;
                    ultimo.sig = primero;
                } else if (actual == ultimo) {
                    ultimo = ultimo.ant;
                    ultimo.sig = primero;
                    primero.ant = ultimo;
                } else {
                    actual.ant.sig = actual.sig;
                    actual.sig.ant = actual.ant;
                }
                return true;
            }
            actual = actual.sig;
        } while (actual != primero);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
