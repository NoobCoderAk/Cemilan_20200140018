/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.cemilan;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ABID
 */
@Controller
public class ProsesData {    
    @RequestMapping("/input")
    public String getInput(HttpServletRequest data, Model sayur){
        String namasayur = data.getParameter("var_namasayur");
        String hargasayur = data.getParameter("var_hargaperkilogram");
        String jumlahsayur = data.getParameter("var_jumlahbeli");
        String UangCustomer = data.getParameter("var_uangcustomer");
        String StatusKembalian = data.getParameter("var_statuskembalian");
        
        
        Integer pcsayur = Integer.valueOf(hargasayur);
        Integer cjsayur = Integer.valueOf(jumlahsayur);
        Integer upembeli = Integer.valueOf(UangCustomer);
        Integer jumlahbayar = pcsayur * cjsayur;
        Integer totalbayar = null;
        Integer diskon = 0;
        Integer hargadiskon = 0;
        
        
        
        if(jumlahbayar < 16000){
            totalbayar = jumlahbayar - (jumlahbayar*diskon/100);
            hargadiskon = jumlahbayar*diskon/100;
            
        }
        else if(jumlahbayar < 25000){
            diskon = 10;
            totalbayar = jumlahbayar - (jumlahbayar*diskon/100);
            hargadiskon = jumlahbayar*diskon/100;
            
        }
        else{
            diskon = 15;
            totalbayar = jumlahbayar - (jumlahbayar*diskon/100);
            hargadiskon = jumlahbayar*diskon/100;
        }
        
        
        Integer kembalian = upembeli - totalbayar;
        
        if (upembeli == totalbayar){
            StatusKembalian ="Uang anda pas";
        }
        else if(upembeli > totalbayar){
            StatusKembalian ="kembalian anda "+kembalian;
        }
        else{
            StatusKembalian ="Maaf uang anda kurang "+kembalian;
        }
        
        
        sayur.addAttribute("name",namasayur);
        sayur.addAttribute("price",pcsayur);
        sayur.addAttribute("kilo",cjsayur);
        sayur.addAttribute("tbayar",totalbayar); 
        sayur.addAttribute("discountrp", hargadiskon);
        sayur.addAttribute("disc", diskon);
        sayur.addAttribute("total0", jumlahbayar);
        sayur.addAttribute("upembeli", upembeli);
        sayur.addAttribute("kembalian", StatusKembalian);
        return "StrukBelanja";
    }
}
