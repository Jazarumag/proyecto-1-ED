/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;

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
public class ArrayListZ<E> implements List<E> {
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
        int movimientos=n-indice-1;
        if(movimientos==0) return false;
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
        return arreglo[index];
    }

    @Override
    public E set(int index, E element) {
        E previo=arreglo[index];
        arreglo[index]=element;
        return previo;
    }

    private boolean agregarEspacio(int indice){
        int movimientos=n-indice;
        if(movimientos-1==0) return false;
        System.arraycopy(arreglo,indice,arreglo,indice+1,movimientos);
        arreglo[indice]=null;
        return true;
    }
    
    @Override
    public void add(int index, E element) {
        if(n>=MaxSize)
            crecer();
        agregarEspacio(index);
        arreglo[index]=element;
    }

    @Override
    public E remove(int index) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        ArrayListZ<E> subLista = new ArrayListZ<>();
        for (int i = fromIndex; i < toIndex; i++){
            subLista.add(arreglo[i]);
        }
        return subLista;
    }
}
