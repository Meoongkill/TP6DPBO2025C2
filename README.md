# TP6DPBO2025C2

# Janji Desain dan Pemrograman Berorientasi Objek
Saya Muhammad Fathan Putra dengan NIM 2300330 mengerjakan soal Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
Program ini merupakan aplikasi Graphical User Interface (GUI) berbasis bahasa JAVA, untuk permainan Game "Flappy Bird". User diarahkan untuk mengendalikan Karakter (Yae Miko) yang harus terus terbang untuk menghindari obstacles yang bermunculan secara random. Program ini mencangkup berbagai kebutuhan komponen grafis untuk menampilkan karakter, Pipe/obstacle, latar belakang, serta kontrol dalam permainan. 

Program terdiri dari 6 class, yaitu 
- App.java
- FlappyBird.java
- Main.java
- Pipe.java
- Player.java
- StartMenu.java
Yang menjadi bagian yang bisa dijalankan adalah App.java.

# App.java
Kelas App adalah class yang berfungsi untuk menjalankan Start Menu GUI ini. Ketika menjalankan class ini, akan memunculkan Menu ini. 

# StartMenu.java
Kelas StartMenu adalah class yang berfungsi sebagai GUI untuk mengejar nilai bonus +20., yang akan menampilkan GUI Form berisi 2 buah tombol (mainkan gamenya dan About Us). Yang ketika ditekan akan menutup dirinya sendiri, dan akan membuka JFrame game. Di bagian "About us" ada button yang merefrensikan credit dari pembuat game, dan di bagian "mainkan gamenya" ada button yang merefrensikan JFrame game. 

# Player.java
Kelas Player adalah class yang berfungsi untuk merepresentasikan player (burung) yang kita mainkan. Class Player berfungsi untuk menyimpan data posisi, ukuran, gambar, dan kecepatan jatuhnya burung. Buat ukuran ada atribut posX dan poxY, untuk menentukan ukuran / hitbox, ada atribut width dan height, untuk imajiner player ada image buat gambar burung / Yae Mikonya, dan untuk menentukan kecepatan jatuhnya burung, ada velocityY . Ada setter getter biasa untuk mengambil dan mengembalikan nilai dari setiap atribut. 

# Pipe.java
Kelas Player adalah class yang berfungsi untuk merepresentasikan pipe (obstacles) yang akan menjadi hambatan player. Class Pipe berfungsi untuk menyimpan data posisi, ukuran, dan kecepatan munculnya pipa. Buat ukuran ada atribut posX dan posY, untuk menentukan ukuran / obstacles, ada atribut width dan height, untuk imajiner obstacles ada image buat gambar pipanya, dan untuk menentukan kecepatan munculnya pipa, ada velocityX. Ada setter getter biasa untuk mengambil dan mengembalikan nilai dari setiap atribut. 

# FlappyBird.java
Kelas FlappyBird adalah class yang berisi logika-logika game yang ada, mulai
- Bentukan interface (seperti ukuran handphone android)
- Kumpulan atribut yang merefrensikan gambar-gambar asset (burung, pipa, tulisan, background, angka)
- Gameloop pipa, Timer Munculnya pipa, gravitasi, saat game over, saat game dimulai, score, dan restart game.

Kelas ini hana menerima input user berdasarkan Keylistener (R), dan ActionListener (Spasi), untuk mouse tidak bisa dideteksi disini. FlappyBird ini adalah otak dari segala aspek permainan, mulai dari logika hingga tampilan grafis (karena saya masih belum bisa menemukan cara jika tampilan grafisnya dipisah).

# Penjelasan Alur
Jadi gini, ketika program dijalankan, player akan disuguhkan tampilan seperti game bajakan. akan ada Mainkan Gamenya + About us yang bisa user tekan. 
- Jika user menekan About us, akan menuju JFrame sederhana yang bisa di close, yang berisi data diri dari pembuat game.
- Jika user menekan tombool Mainkan Gamenya, akan menutup tampilan ini dan akan membuka JFrame Flappy bird.

Ketika menuju JFrame FlappyBird, akan tampil tulisan Get Ready, dan game tidak akan dimulai sebelum menerima inputan user berupa spasi. Waktu game statis, sehingga tingkat kesukaran di set medium selalu. Setiap user berhasil menghindari obstacles, akan menambah score poin. lalu ketika mati, akan menampilkan tulisan game over dan juga akan mereset timer dan score dari awal kembali. Terdapat opsi Restart dengan menekan tombol R. 

# Hukum-hukum Logika Game
- Score ditambahkan secara dinamis, berdasarkan jumlah obstacle yang berhasil dilewati
- Yang menyebabkan kematian adalah jatuh dibawah Jframe dan nabrak pipa atas maupun bawah
- User bisa loncat sampai ujung frame atas, dan tidak mati
- Bisa restart kalau mati
- Sebelum gamenya mulai, bakalan nampilin pesan get ready. dan Game tidak akan mulai sebelum nerima inputan user
- Score dibuat dinamis, jadi angkanya bisa 1 digit, 2 digit, mungkin 3 digit. dan image nya mengikuti score aslinya
  
# Dokumentasi Program Dijalankan
Tampilan Game ketika Dijalankan
![1  Intellij_Tampilan ketika codingan dijalankan](https://github.com/user-attachments/assets/33744bf2-84b7-447e-b7ed-6cfe276ad928)

Tampilan ketika User menekan Tombol About Us
![2  Intellij_Tampilan ketika User menekan tombol About Us](https://github.com/user-attachments/assets/a9a4f4a0-272b-4574-8dc7-e2d53878e90f)

Tampilan ketika User menekan tombol close sesudah menekan tombol about us
![3  Intellij_Tampilan ketika User menekan tombol close saat menekan tombol About Us](https://github.com/user-attachments/assets/05b47249-8a75-44b2-af7e-9c44eea01aaa)

Tampilan ketika User menekan timbol Main Gamenya!
![4  Intellij_Tampilan ketika User menekan tombol Main Gamenya!](https://github.com/user-attachments/assets/6f31b5a6-3e3d-41d6-b0ec-71399f0acd09)

Tampilan ketika user berhasil melewati obstacle
![5  Intellij_Tampilan ketika User berhasil melewati obstacle](https://github.com/user-attachments/assets/b91d1006-0860-4b9e-8295-1adc4c8ec02a)

Tampilan ketika user game over
![6  Intellij_Tampilan ketika User game over (kena pipa)](https://github.com/user-attachments/assets/785ddf0e-5e93-40f0-90aa-68f34df9326e)

Tampilan ketika user menekan tombol R ketika mati
![7  Intellij_Tampilan ketika User menekan tombol R ketika mati](https://github.com/user-attachments/assets/73977a14-d106-4641-8e69-3801816e20ba)

Tampilan ketika user menekan tombol spasi, setelah ada informasi get ready
![8  Intellij_Tampilan ketika User menekan tombol spasi, setelah ada Informasi Get Ready](https://github.com/user-attachments/assets/d1f8e832-433f-4f64-b9fe-f05f1fae4718)

Tampilan game over ketika player jatuh terlalu dalam
![9  Intellij_Tampilan Game Over ketika Player jatuh terlalu dalam](https://github.com/user-attachments/assets/33f1071f-b961-49f4-b29d-e646f6c37670)

Tampilan tidak mati ketika player terbang terlalu tinggi (tapi tertahan frame)
![10  Intellij_Tampilan Tidak mati ketika Player terbang terlalu tinggi (tapi tertahan)](https://github.com/user-attachments/assets/799d1401-15a8-4ed1-aee4-f82873f66a28)

Tampilan game over jika angkanya diatas 1 digit
![11  Intellij_Tampilan game over jika angkanya diatas 1 digit](https://github.com/user-attachments/assets/76dea117-cbab-45a9-a2a8-51cd9d630a30)
