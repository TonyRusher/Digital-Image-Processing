/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FiltrosLineales;

/**
 *
 * @author tony
 */
public interface iMascarasConvolucion {
    public static final double SQRT2 = Math.sqrt(2.0);
    public static final double [][][] mascaras = 
	{
        {
            {  1,  1,  1 },
            {  1,  1,  1 },
            {  1,  1,  1 }
        },
        {
            {  1,  1,  1 },
            {  1,  2,  1 },
            {  1,  1,  1 } 
        },
        {
            {  1,  2,  1 },
            {  2,  4,  2 },
            {  1,  2,  1 }
        },
        {
            {  1, -2,  1 },
            { -2,  5, -2 },
            {  1, -2,  1 } 
        },
        {
            {  0, -1,  0 },
            { -1,  5, -1 },
            {  0, -1,  0 } 
        },
        {
            { -1, -1, -1 },
            { -1,  9, -1 },
            { -1, -1, -1 }
        },
        {
            {  0,  0, -1 },
            {  0,  1,  0 },
            {  0,  0,  0 } 
        },
        {
            { -1,  0,  0 },
            {  0,  1,  0 },
            {  0,  0,  0 }
        },
        {
            {  1,  0, -1 },
            {  1,  0, -1 },
            {  1,  0, -1 } 
        },
        {
            { -1, -1, -1 },
            {  0,  0,  0 },
            {  1,  1,  1 }
        },
        {
            {  1,  0, -1 },
            {  2,  0, -2 },
            {  1,  0, -1 }
        },
        {
            { -1, -2, -1 },
            {  0,  0,  0 },
            {  1,  2,  1 }
        },
        {
            {   1.0,   0.0,    -1.0 },
            { SQRT2,   0.0,  -SQRT2 },
            {   1.0,   0.0,    -1.0 }
        },
		{
			{ -1.0, -SQRT2,  -1.0 },
			{  0.0,    0.0,   0.0 },
			{  1.0,  SQRT2,   1.0 }
		},
        {
            {  0,  0,  0 },
            {  0,  1, -1 },
            {  0,  0,  0 } 
        },
        {
            {  0, -1,  0 },
            {  0,  1,  0 },
            {  0,  0,  0 }
        },
        {
            {  0,  0,  0 },
            {  1,  0, -1 },
            {  0,  0,  0 } 
        },
        {
            {  0, -1,  0 },
            {  0,  0,  0 },
            {  0,  1,  0 }
        },
        {
            {  1,  1, -1 },
            {  1, -2, -1 },
            {  1,  1, -1 }   
        },
        {
            {  1, -1, -1 },
            {  1, -2, -1 },
            {  1,  1,  1 }
        },
        {
            { -1, -1, -1 },
            {  1, -2,  1 },
            {  1,  1,  1 }
        },
        {
            { -1, -1,  1 },
            { -1, -2,  1 },
            {  1,  1,  1 }
        },
        {
            { -1,  1,  1 },
            { -1, -2,  1 },
            { -1,  1,  1 }
        },
        {
            {  1,  1,  1 },
            { -1, -2,  1 },
            { -1, -1,  1 }
        },
        {
            {  1,  1,  1 },
            {  1, -2,  1 },
            { -1, -1, -1 } 
        },
        {
            {  1,  1,  1 },
            { -1, -2,  1 },
            { -1, -1,  1 }
        },
        {
            {  5, -3, -3 },
            {  5,  0, -3 },
            {  5, -3, -3 }
        },
        {
            { -3, -3, -3 },
            {  5,  0, -3 },
            {  5,  5, -3 }
        },
        {
            { -3, -3, -3 },
            { -3,  0, -3 },
            {  5,  5,  5 }
        },
        {
            { -3, -3, -3 },
            { -3,  0,  5 },
            { -3,  5,  5 }
        },
        {
            { -3, -3,  5 },
            { -3,  0,  5 },
            { -3, -3,  5 }
        },
        {
            { -3,  5,  5 },
            { -3,  0,  5 },
            { -3, -3, -3 }
        },
        {
            {  5,  5,  5 },
            { -3,  0, -3 },
            { -3, -3, -3 }
        },
        {
            {  5,  5, -3 },
            {  5,  0, -3 },
            { -3, -3, -3 }
        },
        {
                {  1,  0, -1 },
                {  1,  0, -1 },
                {  1,  0, -1 }	
        },
        {
                {  0, -1, -1 },
                {  1,  0, -1 },
                {  1,  1,  0 }	
        },
        {
                { -1, -1, -1 },
                {  0,  0,  0 },
                {  1,  1,  1 }
        },
        {
                { -1, -1,  0 },
                { -1,  0,  1 },
                {  0,  1,  1 }	
        },
        {
                { -1,  0,  1 },
                { -1,  0,  1 },
                { -1,  0,  1 }	
        },
        {
                {  0,  1,  1 },
                { -1,  0,  1 },
                { -1, -1,  0 }	
        },
        {
                {  1,  1,  1 },
                {  0,  0,  0 },
                { -1, -1, -1 }	
        },
        {
                { 1,  1,  0},
                { 1,  0, -1},
                { 0, -1, -1}		
        },
        {
                {  1,  0, -1 },
                {  2,  0, -2 },
                {  1,  0, -1 }
        },
        {
                {  0, -1, -2 },
                {  1,  0, -1 },
                {  2,  1,  0 }	
        },
        {
                { -1, -2, -1 },
                {  0,  0,  0 },
                {  1,  2,  1 }	
        },
        {
                { -2, -1,  0 },
                { -1,  0,  1 },
                {  0,  1,  2 }	
        },
        {
                { -1,  0,  1 },
                { -2,  0,  2 },
                { -1,  0,  1 }	
        },
        {
                {  0,  1,  2 },
                { -1,  0,  1 },
                { -2, -1,  0 }	
        },
        {
                {  1,  2,  1 },
                {  0,  0,  0 },
                { -1, -2, -1 }	
        },
        {
                {  2,  1,  0 },
                {  1,  0, -1 },
                {  0, -1, -2 }	
        },
        {
                { -1, -1, -1 },
                {  2,  2,  2 },
                { -1, -1, -1 }	
        },
        {
                {  2, -1, -1 },
                { -1,  2, -1 },
                { -1, -1,  2 }	
        },
        {
                { -1, -1, -1 },
                {  2,  2,  2 },
                { -1, -1, -1 }	
        },
        {
                { -1, -1,  2 },
                { -1,  2, -1 },
                {  2, -1, -1 }	
        },
        {
                { -1,  2,  -1 },
                { -1,  2,  -1 },
                { -1,  2,  -1 }	
        },
        {
                { -1, -1,  -1 },
                {  2,  2,   2 },
                { -1, -1,  -1 }	
        },
        {
                { -1, -1,   2 },
                { -1,  2,  -1 },
                {  2, -1,  -1 }	
        },
        {
                {  2, -1,  -1 },
                { -1,  2,  -1 },
                { -1, -1,   2 }	
        },
        {
                { -1,  2,  -1 },
                { -2,  4,  -2 },
                { -1,  2,  -1 }	
        },
        {
                { -1, -2,  -1 },
                {  2,  4,   2 },
                { -1, -2,  -1 }	
        },
        {
                { -2, -1,   2 },
                { -1,  4,  -1 },
                {  2, -1,  -2 }	
        },
        {
                {  2, -1,  -2 },
                { -1,  4,  -1 },
                { -2, -1,   2 }	
        },
        {
                {  1, -2,   1 },
                { -2,  4,  -2 },
                {  1, -2,   1 }	
        },{
                {  1,  1,  0, -1, -1 },
                {  2,  2,  0, -2, -2 },
                {  3,  3,  0, -3, -3 },
                {  2,  2,  0, -2, -2 },
                {  1,  1,  0, -1, -1 } 	
        },
        {
                {  1,  2,  3,  2,  1 },
                {  1,  2,  3,  2,  1 },
                {  0,  0,  0,  0,  0 },
                { -1, -2, -3, -2, -1 },
                { -1, -2, -3, -2, -1 }	
        },
        {
                {  0,  0,  0,  0,  0 },
                {  0, -1, -1, -1,  0 },
                {  0,  2,  2,  2,  0 },
                {  0, -1, -1, -1,  0 },
                {  0,  0,  0,  0,  0 }
        },
        {
                {  0,  0,  0,  0,  0 },
                {  0,  2, -1, -1,  0 },
                {  0, -1,  2, -1,  0 },
                {  0, -1, -1,  2,  0 },
                {  0,  0,  0,  0,  0 }
        },
        {
                {  0,  0,  0,  0,  0 },
                {  0, -1, -1, -1,  0 },
                {  0,  2,  2,  2,  0 },
                {  0, -1, -1, -1,  0 },
                {  0,  0,  0,  0,  0 }	
        },
        {
                {  0,  0,  0,  0,  0 },
                {  0, -1, -1,  2,  0 },
                {  0, -1,  2, -1,  0 },
                {  0,  2, -1, -1,  0 },
                {  0,  0,  0,  0,  0 }	
        },
        {
                {  0,  0,  0,  0,  0 },
                {  0, -1,  0,  1,  0 },
                {  0, -1,  0,  1,  0 },
                {  0, -1,  0,  1,  0 },
                {  0,  0,  0,  0,  0 }	
        },
        {
                {  0,  0,  0,  0,  0 },
                {  0, -1, -1, -1,  0 },
                {  0,  0,  0,  0,  0 },
                {  0,  1,  1,  1,  0 },
                {  0,  0,  0,  0,  0 }	
        },
        {
                {  1,  1,  1,  0, -1, -1, -1 },
                {  1,  1,  1,  0, -1, -1, -1 },
                {  1,  1,  1,  0, -1, -1, -1 },
                {  1,  1,  1,  0, -1, -1, -1 },
                {  1,  1,  1,  0, -1, -1, -1 },
                {  1,  1,  1,  0, -1, -1, -1 },
                {  1,  1,  1,  0, -1, -1, -1 }	
        },
        {
                {  1,  1,  1,  1,  1,  1,  1 },
                {  1,  1,  1,  1,  1,  1,  1 },
                {  1,  1,  1,  1,  1,  1,  1 },
                {  0,  0,  0,  0,  0,  0,  0 },
                { -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1 }	
        },
        {
                {  1,  1,  1,  0, -1, -1, -1 },
                {  1,  2,  2,  0, -2, -2, -1 },
                {  1,  2,  3,  0, -3, -2, -1 },
                {  1,  2,  3,  0, -3, -2, -1 },
                {  1,  2,  3,  0, -3, -2, -1 },
                {  1,  2,  2,  0, -2, -2, -1 },
                {  1,  1,  1,  0, -1, -1, -1 }	
        },
        {
                {  1,  1,  1,  1,  1,  1,  1 },
                {  1,  2,  2,  2,  2,  2,  1 },
                {  1,  2,  3,  3,  3,  2,  1 },
                {  0,  0,  0,  0,  0,  0,  0 },
                { -1, -2, -3, -3, -3, -2, -1 },
                { -1, -2, -2, -2, -2, -2, -1 },
                { -1, -1, -1, -1, -1, -1, -1 }	
        }
        
    };
    
    public static final String [] mascarasNombres = {
        "Alisamiento suave",
        "Alisamiento medio",
        "Alisamiento fuerte",
        "Definicion suave",
        "Definicion media",
        "Definicion fuerte",
        "Derivada de Roberts para filas.",
        "Derivada de Roberts para columnas.",
        "Derivada de Prewitt para filas",
        "Derivada de Prewitt para columnas",
        "Derivada de Sobel para filas",
        "Derivada de Sobel para columnas",
        "Derivada de Freichen para filas",
        "Derivada de Freichen para columnas",
        "Derivada de diferencia de pixel para filas",
        "Derivada de diferencia de pixel para columnas",
        "Derivada de diferencia de pixel separada para filas",
        "Derivada de diferencia de pixel separada para columnas",
        "Grad. de Prewitt Este",
        "Grad. de Prewitt Noreste",
        "Grad. de Prewitt Norte",
        "Grad. de Prewitt NorOeste",
        "Grad. de Prewitt Oeste",
        "Grad. de Prewitt SurOeste",
        "Grad. de Prewitt Sur",
        "Grad. de Prewitt Sureste",
        "Grad. de Kirsch Este",
        "Grad. de Kirsch Noreste",
        "Grad. de Kirsch Norte",
        "Grad. de Kirsch NorOeste",
        "Grad. de Kirsch Oeste",
        "Grad. de Kirsch SurOeste",
        "Grad. de Kirsch Sur",
        "Grad. de Kirsch Sureste",
        "Grad. de Robinson 3-Este",
        "Grad. de Robinson 3-Noreste",
        "Grad. de Robinson 3-Norte",
        "Grad. de Robinson 3-NorOeste",
        "Grad. de Robinson 3-Oeste",
        "Grad. de Robinson 3-SurOeste",
        "Grad. de Robinson 3-Sur",
        "Grad. de Robinson 3-Sureste",
        "Grad. de Robinson 5-Este",
        "Grad. de Robinson 5-Noreste",
        "Grad. de Robinson 5-Norte",
        "Grad. de Robinson 5-NorOeste",
        "Grad. de Robinson 5-Oeste",
        "Grad. de Robinson 5-SurOeste",
        "Grad. de Robinson 5-Sur",
        "Grad. de Robinson 5-Sureste",
        "Laplaciano 0 ",
        "Laplaciano 45",
        "Laplaciano 90",
        "Laplaciano 135",
        "lineas sin peso H1",
        "lineas sin peso H2",
        "lineas sin peso H3",
        "lineas sin peso H4",
        "lineas con peso H1",
        "lineas con peso H2",
        "lineas con peso H3",
        "lineas con peso H4",
        "operador de prewith",
        "derivada de Prewitt para filas.",
        "derivada de Prewitt para columnas.",
        "Laplaciano 0",
        "Laplaciano 45",
        "Laplaciano 90",
        "Laplaciano 135",
        "Segunda derivada vertical",
        "Segunda derivada horizontal",
        "Derivada de Boxcar para filas",
        "Derivada de Boxcar para columnas",
        "Derivada de Piramid para filas",
        "Derivada de Piramid para columnas",
    };
    
    
}
