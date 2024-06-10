/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.estructuras;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author joshz
 */
public class ArrayListZ<E> implements List<E>, Serializable {
    
    private static final long serialVersionUID=1L;
    private int MaxSize = 10, n=0;
    public E arreglo[];
    public ArrayListZ(){
        this.arreglo = (E[]) new Object[MaxSize];
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return (n==0);
    }

    @Override
    public boolean contains(Object o) {
        for (int i=0; i<n ;i++){
            E elemento=arreglo[i];
            if (o==null ? elemento==null : o.equals(elemento)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListZIterator();
    }
    
    private class ArrayListZIterator implements Iterator<E>{
        private int nActual;
        private boolean puedeRemover;
        
        public ArrayListZIterator(){
            this.nActual=0;
            puedeRemover=false;
        }
        
        @Override
        public boolean hasNext(){
            return nActual < n;
        }
        
        @Override
        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            puedeRemover=true;
            return arreglo[nActual++];
        }
        
        @Override
        public void remove(){
            if(!puedeRemover){
                throw new IllegalStateException();
            }
            ArrayListZ.this.remove(--nActual);
            puedeRemover=false;
        }
    }
    
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arreglo, this.size());
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int tamanio=this.size();
        if(a.length<tamanio){
            return (T[]) Arrays.copyOf(arreglo, tamanio, a.getClass());
        }
        System.arraycopy(arreglo,0,a,0,tamanio);
        if(a.length>tamanio){
            for(int i=tamanio;i<a.length;i++)
                a[i]=null;
        }
        return a;
    }

    private void crecer(){
        MaxSize*=2;
        E[] crecido=(E[]) new Object[MaxSize];
        System.arraycopy(arreglo,0,crecido,0,n);
        arreglo=crecido;
    }
    
    @Override
    public boolean add(E e) {
        if (n >= MaxSize)
            crecer();
        arreglo[n++]=e;
        return true;
    }

    private boolean eliminar(int indice){
        if(indice<0 || indice>=n)
            throw new IndexOutOfBoundsException();
        int movimientos=n-indice-1;
        if(movimientos>0)
            System.arraycopy(arreglo,indice+1,arreglo,indice,movimientos);
        arreglo[--n]=null;
        return true;
    }
    
    @Override
    public boolean remove(Object o) {
        for (int i=0;i<n;i++){
            if (o==null ? arreglo[i]==null:arreglo[i].equals(o)){
                return eliminar(i);
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o:c){
            if(!contains(c))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E e:c)
            this.add(e);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(index<0 || index>=n)
            throw new IndexOutOfBoundsException();
        for(E e:c){
            this.add(index++, e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int contador=0;
        for(Object o:c){
            if(this.remove(o)) contador++;
        }
        return contador>0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int contador=0;
        for(Object o:c){
            if(!this.contains(o)){
                this.remove(o);
                contador++;
            }
        }
        return contador>0;
    }

    @Override
    public void clear() {
        for(int i=0;i<this.size();i++)
            arreglo[i]=null;
        n=0;
    }

    @Override
    public E get(int index) {
        if(index<0 || index>=n)
            throw new IndexOutOfBoundsException();
        return arreglo[index];
    }

    @Override
    public E set(int index, E element) {
        if(index<0 || index>=n)
            throw new IndexOutOfBoundsException();
        E previo=arreglo[index];
        arreglo[index]=element;
        return previo;
    }

    private boolean agregarEspacio(int indice){
        if(indice<0 || indice>=n)
            throw new IndexOutOfBoundsException();
        int movimientos=n-indice;
        if(movimientos-1==0) return false;
        System.arraycopy(arreglo,indice,arreglo,indice+1,movimientos);
        arreglo[indice]=null;
        return true;
    }
    
    @Override
    public void add(int index, E element) {
        if(index<0 || index>=n)
            throw new IndexOutOfBoundsException();
        if(n>=MaxSize)
            crecer();
        agregarEspacio(index);
        arreglo[index]=element;
    }

    @Override
    public E remove(int index) {
        if(index<0 || index>=n)
            throw new IndexOutOfBoundsException();
        E copia=arreglo[index];
        eliminar(index);
        return copia;
    }

    @Override
    public int indexOf(Object o) {
        for(int i=0;i<this.size();i++){
            E elemento=arreglo[i];
            if(o==null ? elemento==null : o.equals(elemento))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int ultimoIndice=-1;
        for(int i=0;i<this.size();i++){
            E elemento=arreglo[i];
            if(o==null ? elemento==null : o.equals(elemento))
                ultimoIndice=i;
        }
        return ultimoIndice;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < n;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arreglo[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                return arreglo[--index];
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(E e) {
                if (index <= 0 || index > n) {
                    throw new IllegalStateException();
                }
                arreglo[index - 1] = e;
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if(fromIndex<0 || fromIndex>=n || toIndex<fromIndex)
            throw new IndexOutOfBoundsException();
        ArrayListZ<E> subLista = new ArrayListZ<>();
        for (int i = fromIndex; i < toIndex; i++){
            subLista.add(arreglo[i]);
        }
        return subLista;
    }
    
    @Override
    public String toString(){
        List<E> sublista = this.subList(0,n);
        Object[] array = sublista.toArray();
        return Arrays.toString(array);
    }
    
    public boolean replace(E element) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(element)) {
                this.set(i, element);
                return true;
            }
        }
        return false;
    }
}
