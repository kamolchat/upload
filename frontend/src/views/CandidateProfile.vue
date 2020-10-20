<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-alert type="success" dismissible v-model="message">บันทึกข้อมูลสำเร็จ</v-alert>
           <!-- <v-alert type="error" dismissible v-model="alertFailed">{{alertmsg}}!</v-alert> -->
           
          </v-col>
        </v-row>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลผู้สมัครสมาชิกสภานักศึกษา</h1>


          <v-row justify="center">
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="หมายเลขผู้สมัคร"
            placeholder=" "
            outlined
            v-model="fillCanNumber"
          ></v-text-field>
        </v-col>
        </v-row>

          <v-row justify="center">
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="คำนำหน้าชื่อ"
            placeholder=" "
            outlined
            v-model="fillTitleName"
          ></v-text-field>
        </v-col>

        
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            label="ชื่อ-สกุล"
            placeholder=" "
            outlined
            v-model="fillName"
          ></v-text-field>
        </v-col>
        </v-row>

        <v-row justify="center">
         <v-col cols="6" sm="2">
            <v-select class="pa-0 ma-0" label="เพศ" v-model="selectGender"
              :items="genders" item-text="gender" item-value="gender_id" />
          </v-col>
        
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="วัน-เดือน-ปีเกิด"
            placeholder=" "
            outlined
            v-model="fillBirthday"
          ></v-text-field>
        </v-col>

        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="เบอร์โทรศัพท์"
            placeholder=" "
            outlined
            v-model="fillTelephone"
          ></v-text-field>
          </v-col>
        </v-row>

        <v-row justify="center">
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="รหัสนักศึกษา"
            placeholder=" "
            outlined
            v-model="fillStudentId"
          ></v-text-field>
        </v-col>

        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="ชั้นปี"
            placeholder=" "
            outlined
            v-model="fillYear"
          ></v-text-field>
        </v-col>

            <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="คะแนนเฉลี่ยสะสม(GPAX)"
            placeholder=" "
            outlined
            v-model="fillGrade"
          ></v-text-field>
        </v-col>
        </v-row>

          <v-row justify="center">
          <v-col cols="6">
            <v-select class="pa-0 ma-0" label="สำนักวิชา-สาขาวิชา" v-model="selectMajor"
              :items="majors" item-text="major" item-value="major_id" />
          </v-col>
          </v-row>

        <v-row justify="center">
        <v-col cols="12" sm="6" md="6">
          <v-text-field
            label="กิจกรรมที่เคยร่วม"
            placeholder=" "
            outlined
            v-model="fillArchivement"
          ></v-text-field>
        </v-col>
        </v-row>

        <v-row justify="center">
        <v-col cols="12" sm="6" md="6">
          <v-text-field
            label="จุดมุ่งหมายในการสมัครครั้งนี้"
            placeholder=" "
            outlined
            v-model="fillPurpose"
          ></v-text-field>
        </v-col>
        </v-row>

        
        <v-row justify="center">
        <v-col cols="12" sm="6" md="6">
        <v-file-input 
          :rules="rules"
          accept="image/png, image/jpeg"
          label="รูปภาพ"
          placeholder="  "
          prepend-icon="mdi-camera"
          outlined :show-size="6"

          @change="onFileSelected"
          
        ></v-file-input>
        </v-col>
        </v-row>

          <v-row justify="center">
          <v-col cols="6">
            <v-select
              class="pa-0 ma-0"
              label="ผู้กรอกข้อมูล"
              v-model="selectAdmin"
              :items="admins"
              item-text="name"
              item-value="admin_id"
            />
          </v-col>
        </v-row>
       

        <v-row justify="center">
          <v-col cols="6" sm="6" class="pa-2 mx-3">
            <v-btn @click="save" color="success">SAVE</v-btn>
            &nbsp;
            <v-btn  class="ma-2" router-link to="/">CANCEL</v-btn>
          </v-col>
         </v-row>

      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api.js";
export default {

  mounted() {
    this.getAllAdmin();
    this.getAllGender();
    this.getAllMajor(); 
   // this.clearAlert();
  },
  data() {
    return {
    
        fillTitleName: '',
        fillName: '',
        fillBirthday: '',
        fillTelephone: '',
        fillStudentId: '',
        fillYear: '',
        fillGrade: '',
        fillArchivement: '',
        fillCanNumber: '',
        fillPurpose: '',
        selectedFile: null,

        majors: [],
        selectMajor: null,

        genders: [],
        selectGender: null,
        
        admins: [],
        selectAdmin: null,

        response: null,
        message: null
       //alertFailed: false,
        //alertSuccess: false,
        //alertmsg: undefined
    };
  },
  
  methods: {
    onFileSelected(event) {
      // ให้ภาพที่เราเลือกจากคอม มาเก็บไว้ในตัวแปร selectedFile
      this.selectedFile = event.target.files[0];
    },
    getAllAdmin() {
      api
        .get("/api/admins")
        .then(response => {
          this.admins = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
      },
    getAllGender() {
        api
        .get("/gender")
        .then(response => {
          this.genders = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllMajor() {
        api
        .get("/major")
        .then(response => {
          this.majors = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    /*clearAlert() {
      this.alertmsg = false;
      this.alertFailed = false;
      this.alertSuccess = false;
      
    },*/

    save() {
      const fd = new FormData();
      fd.append('file', this.selectedFile);
      fd.append('title_name', this.fillTitleName);
      fd.append('c_name', this.fillName);
      fd.append('birthday', this.fillBirthday);
      fd.append('telephone', this.fillTelephone);
      fd.append('student_id', this.fillStudentId);
      fd.append('year', this.fillYear);
      fd.append('grade', this.fillGrade);
      fd.append('archivement', this.fillArchivement);
      fd.append('c_number', this.fillCanNumber);
      fd.append('purpose', this.fillPurpose);
      fd.append('major_id', this.selectMajor);
      fd.append('gender_id', this.selectGender);
      fd.append('admin_id', this.selectAdmin);

      console.log(fd);
      var msg = null;
      api
        .post('/api/canp', fd)
        .then((response) => {
          console.log("din")
          console.log(response.data);
          msg = response.data;
        })
        .catch(function(error) {
          console.log(error)
          if (error.response) {
            msg = error.response.data.message;
            console.log(msg)
          } else if (error.request) {
            console.log(error.request);
          } else {
            console.log('Error', error.message);
          }
          console.log(error.config);
        })
        .finally(() => {
          console.log('finaly')
          this.message = msg;
        });
    },
    
  }
};

</script>