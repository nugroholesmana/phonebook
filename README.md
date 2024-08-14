# Proyek CRUD Buku Telepon

Ini adalah aplikasi CRUD sederhana untuk mengelola buku telepon, dibangun menggunakan Java Spring Boot. Aplikasi ini mendukung operasi CRUD dasar seperti menambahkan, melihat, memperbarui, dan menghapus kontak dalam buku telepon.

## Cara Menjalankan Proyek
- **Clone Repository**
- **Sesuaikan konfigurasi DB di file `application.properties`**
- **Buka Command Prompt lalu ketik perintah `mvn spring-boot:run`**
- **Proyek akan running dan akan otomatis generate tabel yang dibutuhkan**

Note: Pastikan Maven dan Java 17 telah terinstal pada komputer

## Fitur

- **Tambah Kontak**: Menambahkan kontak baru ke dalam buku telepon.
- **Lihat Kontak**: Melihat detail dari kontak yang ada berdasarkan ID.
- **Lihat Semua Kontak**: Menampilkan semua kontak yang ada di buku telepon.
- **Perbarui Kontak**: Memperbarui informasi kontak yang ada.
- **Hapus Kontak**: Menghapus kontak dari buku telepon.

## Validasi

- **Nama Depan & Nama Belakang**: Tidak boleh kosong.
- **Nomor Telepon**: Harus valid dengan format 10-15 digit angka, dan harus unik.
- **Email**: Jika diisi, harus valid dan unik.

## Struktur Proyek

- `model`: Berisi entitas `Contact` yang merepresentasikan tabel kontak dalam basis data.
- `repository`: Berisi antarmuka `ContactRepository` yang merupakan perpanjangan dari `JpaRepository` untuk operasi CRUD.
- `service`: Berisi logika bisnis, termasuk validasi data dan pengecekan unik untuk nomor telepon dan email.
- `controller`: Berisi API endpoint untuk mengakses layanan buku telepon.

## Endpoint API

- **Tambah Kontak**  
  `POST /api/contacts`  
  Menambahkan kontak baru ke buku telepon.

- **Lihat Kontak**  
  `GET /api/contacts/{id}`  
  Menampilkan detail kontak berdasarkan ID.

- **Lihat Semua Kontak**  
  `GET /api/contacts`  
  Menampilkan semua kontak yang ada di buku telepon.

- **Perbarui Kontak**  
  `PUT /api/contacts/{id}`  
  Memperbarui detail kontak berdasarkan ID.

- **Hapus Kontak**  
  `DELETE /api/contacts/{id}`  
  Menghapus kontak berdasarkan ID.

## Contoh Respons API

- **Berhasil Menambahkan Kontak**
  ```json
  {
      "statusCode": 201,
      "message": "Contact created successfully",
      "data": {
          "id": 1,
          "firstName": "John",
          "lastName": "Doe",
          "phoneNumber": "081234567890",
          "email": "john.doe@example.com",
          "address": "Jl. Example No. 1",
          "createdAt": "2024-08-15T12:00:00",
          "updatedAt": "2024-08-15T12:00:00"
      }
  }
