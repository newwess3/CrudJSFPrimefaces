/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newwess.agendaalunos.controller;

/**
 *
 * @author weslei
 */
public class PageInfo {
    
    private final int MAXITEM = 10;

    private int maxItemCurrent = 0;
    private int firstItemCurrent = 0;
    private int lastItemCurrent;
    private int itemCount = -1;
    
    

    public PageInfo() {
        maxItemCurrent = MAXITEM;
        lastItemCurrent = maxItemCurrent;
    }

    public int getFirstItemCurrent() {
        return firstItemCurrent;
    }

    public void setFirstItemCurrent(int firstItem) {
        this.firstItemCurrent = firstItem;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getLastItemCurrent() {

        return lastItemCurrent;
    }

    public void setLastItemCurent(int lastItem) {
        this.lastItemCurrent = lastItem;
    }

    public int getMaxItem() {
        return maxItemCurrent;
    }

    public void setMaxItem(int maxItem) {
        this.maxItemCurrent = maxItem;
    }

    private void resetMaxItem() {
        maxItemCurrent = MAXITEM;
    }

    public void next() {
        resetMaxItem();

//        firstItemCurrent = firstItemCurrent + maxItem;
        firstItemCurrent += maxItemCurrent;
        if (lastItemCurrent + maxItemCurrent > itemCount) {
            lastItemCurrent = itemCount;
        } else {
//            lastItemCurrent = lastItemCurrent + maxItem;
            lastItemCurrent += maxItemCurrent;
        }
    }

    public void prev() {
        resetMaxItem();

//        firstItemCurrent = firstItemCurrent - maxItem;
        
        if (lastItemCurrent == itemCount && (itemCount - firstItemCurrent) < maxItemCurrent) {
            lastItemCurrent = (itemCount / maxItemCurrent) * maxItemCurrent;
        } else {
//        lastItemCurrent = lastItemCurrent - maxItem;
            lastItemCurrent -= maxItemCurrent;
        }
        firstItemCurrent -= maxItemCurrent;
    }

    public void first() {
        resetMaxItem();

        firstItemCurrent = 0;
        lastItemCurrent = maxItemCurrent;
    }

    public void last() {
        if ((itemCount / maxItemCurrent) * maxItemCurrent == itemCount) {
            firstItemCurrent = itemCount - maxItemCurrent;
            lastItemCurrent = itemCount;
        } else {
            firstItemCurrent = (itemCount / maxItemCurrent) * maxItemCurrent;
            maxItemCurrent = calcMaxLast();
        }

//        firstItemCurrent = itemCount - maxItem;        
        lastItemCurrent = itemCount;

    }
    
    private int calcLastItems(){
        return itemCount - firstItemCurrent;
    }

    private int calcMaxLast() {
        int res = itemCount % maxItemCurrent;
        int pagInteira = itemCount / maxItemCurrent;
        boolean bool = res > 0 ? true : false;

//        int maxLast;
        if (bool) {
            return itemCount - (pagInteira * maxItemCurrent);
        } else {
            return maxItemCurrent;
        }

//       return maxLast;
    }
}
