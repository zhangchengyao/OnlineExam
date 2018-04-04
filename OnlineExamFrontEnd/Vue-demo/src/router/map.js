const Dist = {
  template: '<router-view></router-view>'
}

export default {
  mode: 'history',
  routes: [
    {
      path: 'login',
      name: 'LoginPage',
      component (resolve) {
        require(['@/page/auth/LoginPage'], resolve)
      },
      meta: { requiresAuth: false }
    },
    {
      path: '/teacher',
      component: Dist,
      children: [
        {
          path: 'login',
          name: 'teacherLoginPage',
          component (resolve) {
            require(['@/page/auth/LoginPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'register',
          name: 'RegisterTeacherPage',
          component (resolve) {
            require(['@/page/auth/RegisterTeacherPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'question/:teacherId',
          name: 'QuestionListPage',
          component (resolve) {
            require(['@/page/teacher/QuestionListPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'question/detail/:teacherId/:examId',
          name: 'QuestionListDetailPage',
          component (resolve) {
            require(['@/page/teacher/QuestionListDetailPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'question/upload/:teacherId/:examId',
          name: 'QuestionUploadPage',
          component (resolve) {
            require(['@/page/teacher/QuestionUploadPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'exam/generate/:teacherId',
          name: 'ExamGenerate',
          component (resolve) {
            require(['@/page/teacher/ExamGeneratePage'], resolve)
          },
          meta: { requiresAuth: false }
        }
      ]
    },
    {
      path: '/student',
      component: Dist,
      children: [
        {
          path: 'login',
          name: 'studentLoginPage',
          component (resolve) {
            require(['@/page/auth/LoginPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'register',
          name: 'RegisterStudentPage',
          component (resolve) {
            require(['@/page/auth/RegisterStudentPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'exam/list/:studentId',
          name: 'ExamList',
          component (resolve) {
            require(['@/page/student/ExamListPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'exam/:studentId/:examId/question/:questionId/:first',
          name: 'ExamQuestion',
          props: true,
          component (resolve) {
            require(['@/page/student/ExamQuestionPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'exam/:examId/check/:answer/:mark',
          name: 'ExamCheck',
          props: true,
          component (resolve) {
            require(['@/page/student/ExamCheckPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'exam/:studentId/list/:examId/info',
          name: 'ExamInfo',
          props: true,
          component (resolve) {
            require(['@/page/student/ExamInfoPage'], resolve)
          },
          meta: { requiresAuth: false }
        },
        {
          path: 'exam/:studentId/:examId/paper',
          name: 'ExamPaper',
          props: true,
          component (resolve) {
            require(['@/page/student/ExamPaperPage'], resolve)
          },
          meta: { requiresAuth: false }
        }
      ]
    }
  ]
}
