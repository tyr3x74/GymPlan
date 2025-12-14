# GymPlan
Topik : GymPlan

Mata Kuliah : Algoritma dan Struktur Data

Kelas : D

Dosen Pengampu : Renny Pradina K.

Aggota Kelompok 8:
1.	Jiersa Hilal Indrasastra	    5026241191
2.	Raditya Prazenko Reswara    	5026241155
3.	Ida Bagus Putu Dharma Yudhana	5026241195
4.	Nadya Dwi Rahmadhani	        5026241041
----------------------------------------------

*BACKGROUND*

Gaya hidup sehat dan kebutuhan untuk menjaga kebugaran fisik semakin meningkat di kalangan masyarakat. Banyak orang yang mulai berolahraga di untuk mencapai berbagai tujuan seperti menurunkan berat badan, Mengontrol nutrisi harian, dan menjaga kesehatan tubuh. Kami ingin membuat sebuah sistem sederhana berbasis command line (CLI) yang  membantu dalam merekomendasikan jadwal latihan yang sesuai dengan data personal pengguna

*SOLUSI*

GymPlan menawarkan solusi berupa aplikasi berbasis Command Line Interface (CLI) yang mampu membantu pengguna dalam:

1. Mengolah data profile (usia, berat badan, tinggi badan, body fat, dan frekuensi latihan).
2. Menentukan goal fitness yang sesuai (Cutting, Bulking, atau Recomposition).
3. Memberikan rekomendasi nutrisi harian dan jadwal latihan mingguan yang terstruktur.

*FITUR-FITUR*

1. Perhitungan Profil Kesehatan
2. Penentuan Goal Fitness Otomatis
3. Rekomendasi Nutrisi Harian
4. Rekomendasi Jadwal Latihan Mingguan

*ALGORITMA & STRUKTUR DATA*

1. Binary Search Tree (BST)

    Struktur Data: Binary Search Tree.
    
    Algoritma: Insertion dan traversal inorder.
    
    Fungsi: Menyimpan dan mengurutkan workout berdasarkan tingkat kesulitan.
    
    Implementasi:
    - Class WorkoutBST
    - Method insert(), printInOrder(), findInRange()
    - File ScheduleGenerator.java.
  
      <img width="531" height="533" alt="image" src="https://github.com/user-attachments/assets/f5243a8c-057d-4e72-a4dc-5b784eb4b8bc" />


2. Graph (Relasi Kelompok Otot)

    Struktur Data: Graph berbasis adjacency list.
    
    Algoritma: Breadth-First Search (BFS).
    
    Fungsi: Menyusun urutan latihan mingguan berdasarkan keterkaitan antar muscle group.
    
    Implementasi:
    - Class MuscleGraph
    - Method buildSplit()
    - File ScheduleGenerator.java.
      
       <img width="525" height="693" alt="image" src="https://github.com/user-attachments/assets/822bfbef-43b1-4fc5-94f6-7cacbaa986f4" />


3. Sorting (Bubble Sort)

    Algoritma: Bubble Sort.
    
    Fungsi: Mengurutkan latihan berdasarkan prioritas goal (Main, Support, Optional).
    
    Implementasi:
    - Method bubbleSortByPriority()
    - File ScheduleGenerator.java.
  
      <img width="558" height="412" alt="image" src="https://github.com/user-attachments/assets/0e4c53dc-1a5e-4a75-b7ec-79cce3701516" />






