<template>
  <div id="app">
    <h1>Upload File.</h1>
    <p>This is an uploading file page.</p>
    <div>
      <input type="file" name="file" @change="onFileSelected" />
      <button @click="onUpload">Upload</button>
    </div>
    <div>{{ message }}</div>
  </div>
</template>

<script>
import api from "../Api.js";
export default {
  name: "uploadfile",
  data() {
    return {
      selectedFile: null,
      response: null,
      message: null
    };
  },
  methods: {
    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload() {
      const fd = new FormData();
      fd.append("file", this.selectedFile);
      var msg = null;
      api
        .post("/upload", fd)
        .then(response => {
          console.log(response);
          msg = response.data;
        })
        .catch(function(error) {
          if (error.response) {
            msg = error.response.data.message;
          } else if (error.request) {
            console.log(error.request);
          } else {
            console.log("Error", error.message);
          }
          console.log(error.config);
        })
        .finally(() => {
          this.message = msg;
        });
    }
  }
};
</script>

<style></style>